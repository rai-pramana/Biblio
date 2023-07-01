package com.progmob_d_kelompok_8.biblio.adminuser;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;

public class DetailUserActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;

    ImageButton btEdit, btDelete;
    TextView tvNama
            , tvRole
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        session = new Session(DetailUserActivity.this);
        db = new DatabaseHelper(DetailUserActivity.this);

        btEdit = findViewById(R.id.bt_edit);
        btDelete = findViewById(R.id.bt_delete);

        tvNama = findViewById(R.id.tv_nama);
        tvRole = findViewById(R.id.tv_role);
        tvEmail = findViewById(R.id.tv_email);
        tvNoHP = findViewById(R.id.tv_no_hp);
        tvGender = findViewById(R.id.tv_gender);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvTglGabung = findViewById(R.id.tv_tanggal_gabung);
        tvSelesai = findViewById(R.id.tv_selesai);
        tvRencanaDibaca = findViewById(R.id.tv_rencana_dibaca);
        tvSedangDibaca = findViewById(R.id.tv_sedang_dibaca);
        tvDijatuhkan = findViewById(R.id.tv_dijatuhkan);
        tvSkorMean = findViewById(R.id.tv_skor_mean);
        ivImg = findViewById(R.id.iv_foto);

        showButton();
        setButton();
        showData();

    }

    @Override
    public void onResume() {
        super.onResume();

        showButton();
        setButton();
        showData();
    }

    private void showButton() {
        btEdit.setVisibility(View.VISIBLE);
        btDelete.setVisibility(View.VISIBLE);
        tvRole.setVisibility(View.VISIBLE);
    }

    private void setButton() {

        if (session.isUserAdmin()){
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    session.setFromAdminProfileFragment(false);
                    session.setFromUserProfileFragment(false);
                    startActivity(new Intent(DetailUserActivity.this, UserUpdateActivity.class));
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogDelete();
                }
            });

        } else {
            btEdit.setVisibility(View.GONE);
            btDelete.setVisibility(View.GONE);
            tvRole.setVisibility(View.GONE);
        }
    }

    private void showData() {
        Cursor cursor = db.getOneUserData(session.getUserIdDetail());
        cursor.moveToFirst();

//        int id_pengguna = cursor.getInt(0);
        String role = cursor.getString(1);
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
        tvRole.setText(role);
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

    private void showDialogDelete(){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(DetailUserActivity.this);

        dialogDelete.setTitle("Peringatan!");
        dialogDelete.setMessage("Apakah Anda yakin menghapus pengguna " + nama + "?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteOneUser(session.getUserIdDetail());
                db.updateAllBookStatistic();

                db.close();

                Toast.makeText(getApplicationContext(), "Pengguna " + nama + " berhasil dihapus",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
}