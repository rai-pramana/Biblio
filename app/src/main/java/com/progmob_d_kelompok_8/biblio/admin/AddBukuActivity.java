package com.progmob_d_kelompok_8.biblio.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

public class AddBukuActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_buku);

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
                ActivityCompat.requestPermissions(AddBukuActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });

        btAddBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
    }

    public void clearView(){
        etJenisBuku.setText("");
        etGenre.setText("");
        etPenulis.setText("");
        etPenerbit.setText("");
        etJudul.setText("");
        etTglTerbit.setText("");
        etSinopsis.setText("");
        chooseImageView.setImageResource(R.drawable.baseline_image_24);
        isImageExist = false;
    }

//    public byte[] imageViewToByte(ImageView image) {
//        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//
//        return byteArray;
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                displayToast("Anda tidak memiliki izin untuk mengakses file!");
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                chooseImageView.setImageBitmap(bitmap);
                isImageExist = true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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