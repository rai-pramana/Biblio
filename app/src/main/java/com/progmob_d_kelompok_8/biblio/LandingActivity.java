package com.progmob_d_kelompok_8.biblio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.progmob_d_kelompok_8.biblio.admin.MainAdminActivity;
import com.progmob_d_kelompok_8.biblio.tool.Session;
import com.progmob_d_kelompok_8.biblio.user.MainUserActivity;

public class LandingActivity extends AppCompatActivity {

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        session = new Session(this);

        if(!session.loggedin()){
            logout();
        }
        else if (!session.isUserAdmin()) {
            startActivity(new Intent(LandingActivity.this, MainUserActivity.class));
            finish();

        } else {
            startActivity(new Intent(LandingActivity.this, MainAdminActivity.class));
            finish();

        }
    }

    public void logout(){
        session.setLoggedin(false);
        session.setUserAdmin(false);
        session.setUserId(0);

        startActivity(new Intent(LandingActivity.this, LoginActivity.class));
        finish();
    }
}