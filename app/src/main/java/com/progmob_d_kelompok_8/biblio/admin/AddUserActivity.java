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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.ImageTool;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddUserActivity extends AppCompatActivity {

    private DatabaseHelper db;
    final int REQUEST_CODE_GALLERY = 999;
    private boolean isImageExist = false;
    EditText etNama, etEmail, etNoHP, etAlamat, etPassword;
    Button btAddUser;
    ImageView chooseImageView;
    RadioGroup rgJenisKelamin, rgRole;
    RadioButton rbJenisKelamin, rbRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        db = new DatabaseHelper(this);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNoHP = findViewById(R.id.etNoHP);
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin);
        etAlamat = findViewById(R.id.etAlamat);
        rgRole = findViewById(R.id.rgRole);
        etPassword = findViewById(R.id.etPassword);
        btAddUser = findViewById(R.id.btAddUser);
        chooseImageView = findViewById(R.id.chooseImageView);

        chooseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(AddUserActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });

        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
    }

    public void clearView(){
        etNama.setText("");
        etEmail.setText("");
        etNoHP.setText("");
        etAlamat.setText("");
        etPassword.setText("");
        chooseImageView.setImageResource(R.drawable.baseline_image_24);
        isImageExist = false;
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
        String nama = etNama.getText().toString();
        String email = etEmail.getText().toString();
        String noHP = etNoHP.getText().toString();
        String alamat = etAlamat.getText().toString();
        String password = etPassword.getText().toString();

        rbJenisKelamin = findViewById(rgJenisKelamin.getCheckedRadioButtonId());
        String jenisKelamin = rbJenisKelamin.getText().toString();

        rbRole = findViewById(rgRole.getCheckedRadioButtonId());
        String role = rbRole.getText().toString();

        if(nama.isEmpty()
                || email.isEmpty()
                || noHP.isEmpty()
                || jenisKelamin.isEmpty()
                || alamat.isEmpty()
                || role.isEmpty()
                || password.isEmpty()
                || !isImageExist
        ) {
            displayToast("Lengkapi data yang kosong!");

        } else if (db.getUser(email, password)) {
            displayToast("Pengguna sudah ada");

        } else {
            db.addUserData(role
                    , nama
                    , email
                    , password
                    , noHP
                    , jenisKelamin
                    , alamat
                    , ImageTool.imageViewToByte(chooseImageView));

            clearView();

            displayToast("Pengguna " + nama + " berhasil ditambahkan");
        }
    }
}