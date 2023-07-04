package com.progmob_d_kelompok_8.biblio.adminuser;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

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
    EditText etNama, etEmail, etNoHP, etAlamat, etPassword;
    Button btUpdateUser;
    ImageView chooseImageView;
    RadioGroup rgJenisKelamin, rgRole;
    RadioButton rbJenisKelamin, rbRole, rbJKPria, rbJKWanita, rbRolePengguna, rbRoleAdmin;
    TextView tvRole;

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
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin);
        etAlamat = findViewById(R.id.etAlamatEdit);
        rgRole = findViewById(R.id.rgRole);
        etPassword = findViewById(R.id.etPasswordEdit);
        btUpdateUser = findViewById(R.id.btUpdateUserEdit);
        chooseImageView = findViewById(R.id.ivEdit);
        tvRole = findViewById(R.id.tvRole);
        rbJKPria = findViewById(R.id.rbJKPria);
        rbJKWanita = findViewById(R.id.rbJKWanita);
        rbRolePengguna = findViewById(R.id.rbRolePengguna);
        rbRoleAdmin = findViewById(R.id.rbRoleAdmin);

        if (session.isFromUserProfileFragment()) {
            tvRole.setVisibility(View.GONE);
            rgRole.setVisibility(View.GONE);
        }

        setView();

        chooseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                pickImageActivityResultLauncher.launch(intent);
            }
        });

        btUpdateUser.setOnClickListener(new View.OnClickListener() {
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

        if (cursor.getBlob(8) != null){
            byte[] foto = cursor.getBlob(8);
            Bitmap bitmap = BitmapFactory.decodeByteArray(foto,0, foto.length);
            chooseImageView.setImageBitmap(bitmap);
        }
//        String tgl_gabung = cursor.getString(9);
//        int selesai = cursor.getInt(10);
//        int rencana_dibaca = cursor.getInt(11);
//        int sedang_dibaca = cursor.getInt(12);
//        int dijatuhkan = cursor.getInt(13);
//        float skor_mean = cursor.getFloat(14);

        cursor.close();
        db.close();

        etNama.setText(nama);
        etEmail.setText(email);
        etNoHP.setText(no_hp);

        if (jenis_kelamin.equals("Pria")) rbJKPria.setChecked(true);
        else rbJKWanita.setChecked(true);

        etAlamat.setText(alamat);

        if (role.equals("Pengguna")) rbRolePengguna.setChecked(true);
        else rbRoleAdmin.setChecked(true);

        etPassword.setText(password_pengguna);

        setTitle("Update Akun " + nama);
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void updateData(){
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

            displayToast("Pengguna " + nama + " berhasil diupdate");

            if (session.isFromAdminProfileFragment()) session.setFromAdminProfileFragment(false);
            else if (session.isFromUserProfileFragment()) session.setFromUserProfileFragment(false);

            finish();
        }
    }
}