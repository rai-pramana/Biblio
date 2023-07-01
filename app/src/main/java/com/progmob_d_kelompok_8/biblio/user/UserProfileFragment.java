package com.progmob_d_kelompok_8.biblio.user;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.adminuser.UserUpdateActivity;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;

public class UserProfileFragment extends Fragment {

    private DatabaseHelper db;
    private Session session;

    ImageButton btEdit, btLogout;
    TextView tvNama
            , tvEmail
            , tvNoHP
            , tvGender
            , tvAlamat
            , tvTglGabung
            , tvSelesai
            , tvRencanaDibaca
            , tvSedangDibaca
            , tvDijatuhkan
            , tvSkorMean;
    ImageView ivImg;
    String nama;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new Session(getActivity());
        db = new DatabaseHelper(getActivity());

        btEdit = view.findViewById(R.id.bt_edit);
        btLogout = view.findViewById(R.id.bt_Logout);

        tvNama = view.findViewById(R.id.tv_nama);
        tvEmail = view.findViewById(R.id.tv_email);
        tvNoHP = view.findViewById(R.id.tv_no_hp);
        tvGender = view.findViewById(R.id.tv_gender);
        tvAlamat = view.findViewById(R.id.tv_alamat);
        tvTglGabung = view.findViewById(R.id.tv_tanggal_gabung);
        tvSelesai = view.findViewById(R.id.tv_selesai);
        tvRencanaDibaca = view.findViewById(R.id.tv_rencana_dibaca);
        tvSedangDibaca = view.findViewById(R.id.tv_sedang_dibaca);
        tvDijatuhkan = view.findViewById(R.id.tv_dijatuhkan);
        tvSkorMean = view.findViewById(R.id.tv_skor_mean);
        ivImg = view.findViewById(R.id.iv_foto);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setFromUserProfileFragment(true);
                session.setFromAdminProfileFragment(false);
                startActivity(new Intent(getActivity(), UserUpdateActivity.class));
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainUserActivity) getActivity()).logout();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        showData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showData() {
        Cursor cursor = db.getOneUserData(session.getUserId());
        cursor.moveToFirst();

//        int id_pengguna = cursor.getInt(0);
//        String role = cursor.getString(1);
        nama = cursor.getString(2);
        String email = cursor.getString(3);
//        String password_pengguna = cursor.getString(4);
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

        cursor.close();
        db.close();

        Bitmap bitmap = BitmapFactory.decodeByteArray(foto,0, foto.length);

        tvNama.setText(nama);
        tvEmail.setText(email);
        tvNoHP.setText(no_hp);
        tvGender.setText(jenis_kelamin);
        tvAlamat.setText(alamat);
        tvTglGabung.setText(tgl_gabung);
        tvSelesai.setText(String.valueOf(selesai));
        tvRencanaDibaca.setText(String.valueOf(rencana_dibaca));
        tvSedangDibaca.setText(String.valueOf(sedang_dibaca));
        tvDijatuhkan.setText(String.valueOf(dijatuhkan));
        tvSkorMean.setText(String.valueOf(skor_mean));
        ivImg.setImageBitmap(bitmap);
    }
}