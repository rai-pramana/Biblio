package com.progmob_d_kelompok_8.biblio.user;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;

    private String statusFavorite = "Normal";

    TextView tvJudul;
    EditText etReview, etTglMulai, etTglSelesai;
    Button btSave, btDelete;
    RadioGroup rgStatus, rgSkor;
    RadioButton rbStatus, rbSelesai, rbRencanaDibaca, rbSedangDibaca, rbDijatuhkan, rbSkor, rb10, rb9, rb8, rb7, rb6, rb5, rb4, rb3, rb2, rb1, rbNull;
    ToggleButton tbFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        session = new Session(this);
        db = new DatabaseHelper(this);

        tvJudul = findViewById(R.id.tv_judul);
        etReview = findViewById(R.id.et_review);
        etTglMulai = findViewById(R.id.et_tanggal_mulai);
        etTglSelesai = findViewById(R.id.et_tanggal_selesai);
        btSave = findViewById(R.id.bt_save_list);
        btDelete = findViewById(R.id.bt_delete_list);
        rgStatus = findViewById(R.id.rb_group_status);
        rgSkor = findViewById(R.id.rb_group_score);
        tbFavorite = findViewById(R.id.tb_favorite);
        rbSelesai = findViewById(R.id.rb_selesai);
        rbRencanaDibaca = findViewById(R.id.rb_rencana_dibaca);
        rbSedangDibaca = findViewById(R.id.rb_sedang_dibaca);
        rbDijatuhkan = findViewById(R.id.rb_dijatuhkan);
        rb10 = findViewById(R.id.rb_10);
        rb9 = findViewById(R.id.rb_9);
        rb8 = findViewById(R.id.rb_8);
        rb7 = findViewById(R.id.rb_7);
        rb6 = findViewById(R.id.rb_6);
        rb5 = findViewById(R.id.rb_5);
        rb4 = findViewById(R.id.rb_4);
        rb3 = findViewById(R.id.rb_3);
        rb2 = findViewById(R.id.rb_2);
        rb1 = findViewById(R.id.rb_1);
        rbNull = findViewById(R.id.rb_null);

        tvJudul.setText(session.getBookTitle());

        etTglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Pilih Tanggal Mulai")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String date = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(new Date(selection));
                        etTglMulai.setText(date);
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "tag");
            }
        });

        etTglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Pilih Tanggal Selesai")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String date = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(new Date(selection));
                        etTglSelesai.setText(date);
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "tag");
            }
        });

        tbFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) statusFavorite = "Favorite";
                else statusFavorite = "Normal";
            }
        });

        if (!session.isListed()) {
            btDelete.setVisibility(View.GONE);

            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String status_baca = "";
                    if (rgStatus.getCheckedRadioButtonId() != -1) {
                        rbStatus = findViewById(rgStatus.getCheckedRadioButtonId());
                        status_baca = rbStatus.getText().toString();
                    }

                    String skor = "";
                    if (rgSkor.getCheckedRadioButtonId() != -1) {
                        rbSkor = findViewById(rgSkor.getCheckedRadioButtonId());
                        skor = rbSkor.getText().toString();
                    }

                    String review = etReview.getText().toString();

                    String tgl_mulai = etTglMulai.getText().toString();

                    String tgl_selesai = etTglSelesai.getText().toString();
                    if (tgl_selesai.isEmpty()) {
                        tgl_selesai = String.valueOf(java.time.LocalDate.now());
                    }

                    if (status_baca.isEmpty()) {
                        displayToast("Lengkapi status baca");

                    } else {
                        db.addList(session.getUserId()
                                , session.getBookIdDetail()
                                , status_baca
                                , statusFavorite
                                , skor
                                , review
                                , tgl_mulai
                                , tgl_selesai);
                        db.updateAllBookStatistic();
                        db.updateAllUserStatistic();

                        db.close();

                        displayToast(session.getBookTitle() + " ditambahkan pada list");

                        finish();
                    }
                }
            });

        } else {
            setView();

            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rbStatus = findViewById(rgStatus.getCheckedRadioButtonId());
                    String status_baca = rbStatus.getText().toString();

                    rbSkor = findViewById(rgSkor.getCheckedRadioButtonId());
                    String skor = rbSkor.getText().toString();

                    String review = etReview.getText().toString();
                    String tgl_mulai = etTglMulai.getText().toString();
                    String tgl_selesai =etTglSelesai.getText().toString();

                    db.updateList(session.getUserId()
                            , session.getBookIdDetail()
                            , status_baca
                            , statusFavorite
                            , skor
                            , review
                            , tgl_mulai
                            , tgl_selesai);
                    db.updateAllBookStatistic();
                    db.updateAllUserStatistic();

                    db.close();

                    displayToast("Buku "+ session.getBookTitle() + " diperbarui pada list");

                    finish();
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteList(session.getUserId(), session.getBookIdDetail());
                    db.updateAllBookStatistic();
                    db.updateAllUserStatistic();

                    db.close();

                    displayToast("Buku "+ session.getBookTitle() + " dihapus dari list");
                    finish();
                }
            });
        }
    }

    public void setView(){
        Cursor cursor = db.getUserList(session.getUserId(), session.getBookIdDetail());
        cursor.moveToFirst();

//        int id_pengguna = cursor.getInt(0);
//        int id_buku = cursor.getInt(1);
        String status_baca = cursor.getString(2);
        String status_favorit = cursor.getString(3);
        String skor = cursor.getString(4);
        String review = cursor.getString(5);
        String tgl_mulai = cursor.getString(6);
        String tgl_selesai = cursor.getString(7);

        cursor.close();
        db.close();

        etReview.setText(review);
        etTglMulai.setText(tgl_mulai);
        etTglSelesai.setText(tgl_selesai);

        if (status_baca.equals("Selesai")) rbSelesai.setChecked(true);
        else if (status_baca.equals("Rencana Dibaca")) rbRencanaDibaca.setChecked(true);
        else if (status_baca.equals("Sedang Dibaca")) rbSedangDibaca.setChecked(true);
        else rbDijatuhkan.setChecked(true);

        if (skor.equals("10")) rb10.setChecked(true);
        else if (skor.equals("9")) rb9.setChecked(true);
        else if (skor.equals("8")) rb8.setChecked(true);
        else if (skor.equals("7")) rb7.setChecked(true);
        else if (skor.equals("6")) rb6.setChecked(true);
        else if (skor.equals("5")) rb5.setChecked(true);
        else if (skor.equals("4")) rb4.setChecked(true);
        else if (skor.equals("3")) rb3.setChecked(true);
        else if (skor.equals("2")) rb2.setChecked(true);
        else if (skor.equals("1")) rb1.setChecked(true);
        else rbNull.setChecked(true);

        if (status_favorit.equals("Favorite")) tbFavorite.setChecked(true);
        else tbFavorite.setChecked(false);
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}