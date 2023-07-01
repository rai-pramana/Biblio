package com.progmob_d_kelompok_8.biblio.adminuser;

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

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.ImageTool;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserUpdateActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;

    final int REQUEST_CODE_GALLERY = 999;
    private int idUser;
    EditText etNama, etEmail, etNoHP, etJenisKelamin, etAlamat, etRole, etPassword;
    Button btUpdateUser;
    ImageView chooseImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        session = new Session(this);
        db = new DatabaseHelper(this);

        if (session.isFromAdminProfileFragment() || session.isFromUserProfileFragment()) idUser = session.getUserId();
        else idUser = session.getUserIdDetail();

        etNama = findViewById(R.id.etNamaEdit);
        etEmail = findViewById(R.id.etEmailEdit);
        etNoHP = findViewById(R.id.etNoHPEdit);
        etJenisKelamin = findViewById(R.id.etJenisKelaminEdit);
        etAlamat = findViewById(R.id.etAlamatEdit);
        etRole = findViewById(R.id.etRoleEdit);
        etPassword = findViewById(R.id.etPasswordEdit);
        btUpdateUser = findViewById(R.id.btUpdateUserEdit);
        chooseImageView = findViewById(R.id.ivEdit);

        if (session.isFromUserProfileFragment()) etRole.setVisibility(View.GONE);

        setView();

        chooseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(UserUpdateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });

        btUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String noHP = etNoHP.getText().toString();
                String jenisKelamin = etJenisKelamin.getText().toString();
                String alamat = etAlamat.getText().toString();
                String role = etRole.getText().toString();
                String password = etPassword.getText().toString();

                if(nama.isEmpty()
                        || email.isEmpty()
                        || noHP.isEmpty()
                        || jenisKelamin.isEmpty()
                        || alamat.isEmpty()
                        || role.isEmpty()
                        || password.isEmpty()
                ) {
                    displayToast("Lengkapi data yang kosong!");

                } else {
                    db.updateUserData(idUser
                            , role
                            , nama
                            , email
                            , password
                            , noHP
                            , jenisKelamin
                            , alamat
                            , ImageTool.imageViewToByte(chooseImageView));

                    db.close();

                    displayToast("Pengguna " + nama + " diupdate");

                    if (session.isFromAdminProfileFragment()) session.setFromAdminProfileFragment(false);
                    else if (session.isFromUserProfileFragment()) session.setFromUserProfileFragment(false);

                    finish();
                }
            }
        });
    }

    public void setView(){
        Cursor cursor = db.getOneUserData(idUser);
        cursor.moveToFirst();

//        int id_pengguna = cursor.getInt(0);
        String role = cursor.getString(1);
        String nama = cursor.getString(2);
        String email = cursor.getString(3);
        String password_pengguna = cursor.getString(4);
        String no_hp = cursor.getString(5);
        String jenis_kelamin = cursor.getString(6);
        String alamat = cursor.getString(7);
        byte[] foto = cursor.getBlob(8);
//        String tgl_gabung = cursor.getString(9);
//        int selesai = cursor.getInt(10);
//        int rencana_dibaca = cursor.getInt(11);
//        int sedang_dibaca = cursor.getInt(12);
//        int dijatuhkan = cursor.getInt(13);
//        float skor_mean = cursor.getFloat(14);

        cursor.close();
        db.close();

        Bitmap bitmap = BitmapFactory.decodeByteArray(foto,0, foto.length);

        etNama.setText(nama);
        etEmail.setText(email);
        etNoHP.setText(no_hp);
        etJenisKelamin.setText(jenis_kelamin);
        etAlamat.setText(alamat);
        etRole.setText(role);
        etPassword.setText(password_pengguna);
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