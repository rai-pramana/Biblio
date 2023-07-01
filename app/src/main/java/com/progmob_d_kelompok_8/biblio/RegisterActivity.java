package com.progmob_d_kelompok_8.biblio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.ImageTool;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button reg;
    private TextView tvLogin;
    private EditText etEmail, etPass, etPass2;
    ImageView imageView;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        reg = findViewById(R.id.btnReg);
        tvLogin = findViewById(R.id.tvLogin);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etPass2 = findViewById(R.id.etPass2);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        imageView = findViewById(R.id.foto_register);
//        imageView.setImageResource(R.drawable.account);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnReg){
            register();
        } else if(v.getId()==R.id.tvLogin){
            finish();
        }

    }

    private void register(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String pass2 = etPass2.getText().toString();

        if(email.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            displayToast("Lengkapi email/password");

        } else if (!pass.equals(pass2)) {
            displayToast("Password tidak cocok");

        } else if (db.getUser(email, pass)) {
            displayToast("Email sudah digunakan");

        } else {
            db.addUser(email,pass, ImageTool.imageViewToByte(imageView));
            db.close();

            displayToast("Pengguna berhasil didaftarkan");
            finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}