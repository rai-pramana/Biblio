package com.progmob_d_kelompok_8.biblio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.progmob_d_kelompok_8.biblio.admin.MainAdminActivity;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;
import com.progmob_d_kelompok_8.biblio.user.MainUserActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login;
    private TextView register;
    private EditText etEmail, etPass;
    private DatabaseHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);
        db = new DatabaseHelper(this);

        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnReg);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnLogin) {
            String email = etEmail.getText().toString();
            String pass = etPass.getText().toString();

            login(email, pass);
        } else if(v.getId()==R.id.btnReg) {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }

    }

    private void login(String email, String pass){

        if(db.getUser(email, pass)) {
            session.setLoggedin(true);
            session.setUserId(db.getUserId(email, pass));

            if(db.isUserAdmin(session.getUserId())){
                session.setUserAdmin(true);
                startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
                db.close();
                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                finish();

            } else {
                startActivity(new Intent(LoginActivity.this, MainUserActivity.class));
                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                db.close();
                finish();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Email/password salah", Toast.LENGTH_SHORT).show();
        }
    }
}