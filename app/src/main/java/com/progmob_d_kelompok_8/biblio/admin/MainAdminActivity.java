package com.progmob_d_kelompok_8.biblio.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.progmob_d_kelompok_8.biblio.LoginActivity;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.databinding.ActivityMainAdminBinding;
import com.progmob_d_kelompok_8.biblio.tool.Session;
import com.progmob_d_kelompok_8.biblio.user.MainUserActivity;

public class MainAdminActivity extends AppCompatActivity {

    private Session session;

    private ActivityMainAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new Session(this);

        binding = ActivityMainAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view_admin);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_buku, R.id.navigation_pengguna, R.id.navigation_profile_admin)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewAdmin, navController);

        if(!session.loggedin()){
            logout();
        } else if (!session.isUserAdmin()) {
            startActivity(new Intent(MainAdminActivity.this, MainUserActivity.class));
            finish();

        }
    }

    public void logout(){
        session.setLoggedin(false);
        session.setUserId(0);
        session.setUserAdmin(false);

        startActivity(new Intent(MainAdminActivity.this, LoginActivity.class));
        finish();
    }

}