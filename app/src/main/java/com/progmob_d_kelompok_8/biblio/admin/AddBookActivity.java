package com.progmob_d_kelompok_8.biblio.admin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.ImageTool;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddBookActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;
    final int REQUEST_CODE_GALLERY = 999;
    private boolean isImageExist = false;

    EditText etJudul, etTglTerbit, etSinopsis, etPenulis, etPenerbit, etGenre, etJenisBuku;
    Button btAddBuku;
    ImageView chooseImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        session = new Session(this);
        db = new DatabaseHelper(this);

        etJudul = findViewById(R.id.etJudul);
        etTglTerbit = findViewById(R.id.etTglTerbit);
        etSinopsis = findViewById(R.id.etSinopsis);
        etPenulis = findViewById(R.id.etPenulis);
        etPenerbit = findViewById(R.id.etPenerbit);
        etGenre = findViewById(R.id.etGenre);
        etJenisBuku = findViewById(R.id.etJenisBuku);
        btAddBuku = findViewById(R.id.btAddBuku);
        chooseImageView = findViewById(R.id.chooseImageView);

        etTglTerbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Pilih Tanggal Terbit")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String date = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(new Date(selection));
                        etTglTerbit.setText(date);
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "tag");
            }
        });

        chooseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                pickImageActivityResultLauncher.launch(intent);
            }
        });

        btAddBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
    }

    ActivityResultLauncher<Intent> pickImageActivityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK){
                                Intent data = result.getData();
                                Uri uri = data.getData();

                                if (uri != null) {
                                    try {
                                        InputStream inputStream = getContentResolver().openInputStream(uri);

                                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                        chooseImageView.setImageBitmap(bitmap);
                                        isImageExist = true;

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.d("PhotoPicker", "No media selected");
                                }
                            }
                        }
                    });

    public void clearView(){
        etJenisBuku.setText("");
        etGenre.setText("");
        etPenulis.setText("");
        etPenerbit.setText("");
        etJudul.setText("");
        etTglTerbit.setText("");
        etSinopsis.setText("");
        chooseImageView.setImageResource(R.drawable.baseline_add_photo_alternate_24);
        isImageExist = false;
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void addData(){
        String judul = etJudul.getText().toString();
        String tglTerbit = etTglTerbit.getText().toString();
        String sinopsis = etSinopsis.getText().toString();
        String penulis = etPenulis.getText().toString();
        String penerbit = etPenerbit.getText().toString();
        String genre = etGenre.getText().toString();
        String jenisBuku = etJenisBuku.getText().toString();

        if(judul.isEmpty()
                || tglTerbit.isEmpty()
                || sinopsis.isEmpty()
                || penulis.isEmpty()
                || penerbit.isEmpty()
                || genre.isEmpty()
                || jenisBuku.isEmpty()
                || !isImageExist
        ) {
            displayToast("Lengkapi data yang kosong!");

        } else if (db.getBook(judul, tglTerbit)) {
            displayToast("Buku sudah ada");

        } else {
            db.addBook(session.getUserId()
                    , jenisBuku
                    , genre
                    , penulis
                    , penerbit
                    , judul
                    , tglTerbit
                    , sinopsis
                    , ImageTool.imageViewToByte(chooseImageView));

            clearView();

            displayToast("Buku " + judul + " berhasil ditambahkan");
        }
    }
}