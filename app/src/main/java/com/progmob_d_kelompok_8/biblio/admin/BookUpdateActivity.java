package com.progmob_d_kelompok_8.biblio.admin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

public class BookUpdateActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;
    final int REQUEST_CODE_GALLERY = 999;

    EditText etJudul, etTglTerbit, etSinopsis, etPenulis, etPenerbit, etGenre, etJenisBuku;
    Button btUpdateBuku;
    ImageView chooseImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);

        session = new Session(this);
        db = new DatabaseHelper(this);

        etJudul = findViewById(R.id.etjudulEdit);
        etTglTerbit = findViewById(R.id.etTglTerbitEdit);
        etSinopsis = findViewById(R.id.etSinopsisEdit);
        etPenulis = findViewById(R.id.etPenulisEdit);
        etPenerbit = findViewById(R.id.etPenerbitEdit);
        etGenre = findViewById(R.id.etGenreEdit);
        etJenisBuku = findViewById(R.id.etJenisBukuEdit);
        btUpdateBuku = findViewById(R.id.updateBukuEdit);
        chooseImageView = findViewById(R.id.ivEdit);

        setView();

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

        btUpdateBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
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

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.d("PhotoPicker", "No media selected");
                                }
                            }
                        }
                    });

    public void setView(){
        Cursor cursor = db.getBookDate(session.getBookIdDetail());
        cursor.moveToFirst();

//        int idBuku = cursor.getInt(0);
//        int id_pengguna = cursor.getInt(1);
        String namaJenisBuku = cursor.getString(2);
        String namaGenre = cursor.getString(3);
        String namaPenulis = cursor.getString(4);
        String namaPenerbit = cursor.getString(5);
        String judulBuku = cursor.getString(6);
        String tglTerbit = cursor.getString(7);
        String sinopsis = cursor.getString(8);
//        float skor = cursor.getFloat(9);
        byte[] gambarSampul = cursor.getBlob(10);
//        int jumlahPembaca = cursor.getInt(11);

        cursor.close();
        db.close();

        Bitmap bitmap = BitmapFactory.decodeByteArray(gambarSampul,0, gambarSampul.length);

        etJudul.setText(judulBuku);
        etTglTerbit.setText(tglTerbit);
        etSinopsis.setText(sinopsis);
        etJenisBuku.setText(namaJenisBuku);
        etGenre.setText(namaGenre);
        etPenulis.setText(namaPenulis);
        etPenerbit.setText(namaPenerbit);
        chooseImageView.setImageBitmap(bitmap);

        setTitle("Update Buku " + judulBuku);
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void updateData(){
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
        ) {
            displayToast("Lengkapi data yang kosong!");

        } else {
            db.updateBookData(session.getBookIdDetail()
                    , jenisBuku
                    , genre
                    , penulis
                    , penerbit
                    , judul
                    , tglTerbit
                    , sinopsis
                    , ImageTool.imageViewToByte(chooseImageView));

            db.close();

            displayToast("Buku " + judul + " berhasil diupdate");

            finish();
        }
    }
}