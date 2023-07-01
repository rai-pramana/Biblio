package com.progmob_d_kelompok_8.biblio.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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

import com.progmob_d_kelompok_8.biblio.tool.ImageTool;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class BookUpdateActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;
    final int REQUEST_CODE_GALLERY = 999;

    EditText etJudul, etTglTerbit, etSinopsis, etPenulis, etPenerbit, etGenre, etJenisBuku;
    Button btAddBuku;
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
        btAddBuku = findViewById(R.id.updateBukuEdit);
        chooseImageView = findViewById(R.id.ivEdit);

        setView();

        chooseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(BookUpdateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });

        btAddBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    displayToast("Buku " + judul + " Diupdate");

                    finish();
                }
            }
        });
    }

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
    }

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

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}