package com.progmob_d_kelompok_8.biblio.user;

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
import com.progmob_d_kelompok_8.biblio.admin.MainAdminActivity;
import com.progmob_d_kelompok_8.biblio.databinding.ActivityMainUserBinding;
import com.progmob_d_kelompok_8.biblio.tool.Session;

public class MainUserActivity extends AppCompatActivity {

    private Session session;

    private ActivityMainUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new Session(this);

        binding = ActivityMainUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view_user);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_mylist, R.id.navigation_profile_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_user);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewUser, navController);

        if(!session.loggedin()){
            logout();
        } else if (session.isUserAdmin()) {
            startActivity(new Intent(MainUserActivity.this, MainAdminActivity.class));
            finish();

        }
    }

    public void logout(){
        session.setLoggedin(false);
        session.setUserId(0);

        finish();
        startActivity(new Intent(MainUserActivity.this, LoginActivity.class));
    }
}