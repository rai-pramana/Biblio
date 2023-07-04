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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

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
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                pickImageActivityResultLauncher.launch(intent);
            }
        });

        btAddUser.setOnClickListener(new View.OnClickListener() {
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
        etNama.setText("");
        etEmail.setText("");
        etNoHP.setText("");
        etAlamat.setText("");
        etPassword.setText("");
        chooseImageView.setImageResource(R.drawable.baseline_add_photo_alternate_24);
        isImageExist = false;
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