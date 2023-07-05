package com.progmob_d_kelompok_8.biblio.admin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.adminuser.DetailUserActivity;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;
import com.progmob_d_kelompok_8.biblio.model.User;
import com.progmob_d_kelompok_8.biblio.tool.UserListAdapter;

import java.util.ArrayList;


public class UserFragment extends Fragment {

    private DatabaseHelper db;
    private Session session;
    private RecyclerView rvUser;
    private ArrayList<User> list = new ArrayList<>();
    private UserListAdapter listUserAdapter;
    private SearchView searchView;
    private FloatingActionButton add_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new Session(getActivity());
        db = new DatabaseHelper(getActivity());

        rvUser = view.findViewById(R.id.recyclerViewUser);
        rvUser.setHasFixedSize(true);

        searchView =  view.findViewById(R.id.searchViewUser);
        searchView.clearFocus();
        searchView.setQueryHint("Cari Pengguna...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        add_button = view.findViewById(R.id.floatingAddButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddUserActivity.class));
            }
        });

        list.addAll(getListUser());
        showRecyclerList();

    }

    private void filterList(String text) {
        ArrayList<User> filteredList = new ArrayList<>();
        for (User user : list){
            if (user.getNama().toLowerCase().contains(text.toLowerCase())
                    || user.getEmail().toLowerCase().contains(text.toLowerCase())
                    || user.getRole().toLowerCase().contains(text.toLowerCase())
                    || user.getNo_hp().toLowerCase().contains(text.toLowerCase())
                    || user.getAlamat().toLowerCase().contains(text.toLowerCase())
                    || user.getJenis_kelamin().toLowerCase().contains(text.toLowerCase())
                    || user.getTgl_gabung().toLowerCase().contains(text.toLowerCase())
            ){
                filteredList.add(user);
            }
        }

        if (filteredList.isEmpty()){
            listUserAdapter.setFilteredList(filteredList);
            Toast.makeText(getActivity(), "Pengguna tidak ditemukan", Toast.LENGTH_SHORT).show();
        } else {
            listUserAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        list.clear();
        list.addAll(getListUser());
        showRecyclerList();
        listUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        list.clear();
    }

    public ArrayList<User> getListUser() {
        Cursor cursor = db.getAllUserDataUnlessOne(session.getUserId());

        ArrayList<User> listUser = new ArrayList<>();
        while (cursor.moveToNext()){

            int id_pengguna = cursor.getInt(0);
            String role = cursor.getString(1);
            String nama = cursor.getString(2);
            String email = cursor.getString(3);
            String password_pengguna = cursor.getString(4);
            String no_hp = cursor.getString(5);
            String jenis_kelamin = cursor.getString(6);
            String alamat = cursor.getString(7);
            byte[] foto = cursor.getBlob(8);
            String tgl_gabung = cursor.getString(9);
            int selesai = cursor.getInt(10);
            int rencana_dibaca = cursor.getInt(11);
            int sedang_dibaca = cursor.getInt(12);
            int dijatuhkan = cursor.getInt(13);
            float skor_mean = cursor.getFloat(14);

            User user = new User(id_pengguna
                    , selesai
                    , rencana_dibaca
                    , sedang_dibaca
                    , dijatuhkan
                    , role
                    , nama
                    , email
                    , password_pengguna
                    , no_hp
                    , jenis_kelamin
                    , alamat
                    , foto
                    , tgl_gabung
                    , skor_mean);

            listUser.add(user);
        }

        cursor.close();
        db.close();

        return listUser;
    }

    private void moveDetail(User user) {
        session.setUserIdDetail(user.getId_pengguna());
        startActivity(new Intent(getActivity(), DetailUserActivity.class));
    }

    private void showRecyclerList() {
        rvUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        listUserAdapter = new UserListAdapter(list);
        rvUser.setAdapter(listUserAdapter);

        listUserAdapter.setOnItemClickCallback(data -> {
            moveDetail(data);
        });
    }
}