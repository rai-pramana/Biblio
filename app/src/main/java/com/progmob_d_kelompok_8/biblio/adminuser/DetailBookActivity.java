package com.progmob_d_kelompok_8.biblio.adminuser;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.admin.BookUpdateActivity;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;
import com.progmob_d_kelompok_8.biblio.user.ListActivity;

public class DetailBookActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private Session session;

    Button btEdit, btDelete, btListAdd, btListUpdate;
    TextView tvTitle
            , tvRank
            , tvYear
            , tvRating
            , tvSynopsis
            , tvJenisBuku
            , tvGenre
            , tvPenulis
            , tvPenerbit
            , tvPembaca
            , tvReview;
    ImageView ivImg;
    String judulBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        session = new Session(DetailBookActivity.this);
        db = new DatabaseHelper(DetailBookActivity.this);

        btEdit = findViewById(R.id.bt_edit);
        btDelete = findViewById(R.id.bt_delete);
        btListAdd = findViewById(R.id.bt_list_add);
        btListUpdate = findViewById(R.id.bt_list_update);
        tvReview = findViewById(R.id.tv_review);

        tvTitle = findViewById(R.id.tv_title);
        tvRank = findViewById(R.id.tv_rank);
        tvYear = findViewById(R.id.tv_year);
        tvRating = findViewById(R.id.tv_rating);
        tvSynopsis = findViewById(R.id.tv_sinopsis);
        tvJenisBuku = findViewById(R.id.tv_jenis_buku);
        tvGenre = findViewById(R.id.tv_genre);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvPenerbit = findViewById(R.id.tv_penerbit);
        tvPembaca = findViewById(R.id.tv_pembaca);
        ivImg = findViewById(R.id.iv_gambar_sampul);

        showData();
        showButton();
        setButton();

        ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setFrom("DetailBookActivity");
                startActivity(new Intent(DetailBookActivity.this, PreviewImageActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        showData();
        showButton();
        setButton();
    }

    private void showButton() {
        btListAdd.setVisibility(View.VISIBLE);
        btListUpdate.setVisibility(View.VISIBLE);
        btEdit.setVisibility(View.VISIBLE);
        btDelete.setVisibility(View.VISIBLE);
        tvReview.setVisibility(View.VISIBLE);
    }

    private void setButton() {
        if (!db.isBookListed(session.getBookIdDetail())){
            tvReview.setVisibility(View.GONE);
        } else {
            tvReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(DetailBookActivity.this, ReviewActivity.class));
                }
            });
        }

        if (session.isUserAdmin()){
            btListAdd.setVisibility(View.GONE);
            btListUpdate.setVisibility(View.GONE);

            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(DetailBookActivity.this, BookUpdateActivity.class));
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

            if (!db.isListed(session.getUserId(), session.getBookIdDetail())) {
                btListUpdate.setVisibility(View.GONE);

                btListAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        session.setListed(false);
                        startActivity(new Intent(DetailBookActivity.this, ListActivity.class));
                    }
                });

            } else {
                btListAdd.setVisibility(View.GONE);

                btListUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        session.setListed(true);
                        startActivity(new Intent(DetailBookActivity.this, ListActivity.class));
                    }
                });
            }
        }
    }

    private void showData() {
        Cursor cursor = db.getBook(session.getBookIdDetail());
        cursor.moveToFirst();

//            int idBuku = cursor.getInt(0);
//            int id_pengguna = cursor.getInt(1);
        String namaJenisBuku = cursor.getString(2);
        String namaGenre = cursor.getString(3);
        String namaPenulis = cursor.getString(4);
        String namaPenerbit = cursor.getString(5);
        judulBuku = cursor.getString(6);
        String tglTerbit = cursor.getString(7);
        String sinopsis = cursor.getString(8);
        float skor = cursor.getFloat(9);
        byte[] gambarSampul = cursor.getBlob(10);
        int jumlahPembaca = cursor.getInt(11);
        int rank = cursor.getInt(12);

        session.setBookTitle(judulBuku);

        cursor.close();
        db.close();

        Bitmap bitmap = BitmapFactory.decodeByteArray(gambarSampul,0, gambarSampul.length);

        tvTitle.setText(judulBuku);
        tvYear.setText(tglTerbit);
        tvRating.setText(String.valueOf(skor));
        tvSynopsis.setText(sinopsis);
        tvJenisBuku.setText(namaJenisBuku);
        tvGenre.setText(namaGenre);
        tvPenulis.setText(namaPenulis);
        tvPenerbit.setText(namaPenerbit);
        tvPembaca.setText(String.valueOf(jumlahPembaca));
        ivImg.setImageBitmap(bitmap);
        tvRank.setText(String.valueOf(rank));

        setTitle(judulBuku);
    }

    private void showDialogDelete(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(DetailBookActivity.this)
                .setTitle("Peringatan Penghapusan!")
                .setIcon(R.drawable.baseline_notification_important_24)
                .setMessage("Apakah Anda yakin menghapus buku " + judulBuku + "?")
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteOneBook(session.getBookIdDetail());
                        db.updateAllUserStatistic();

                        db.close();

                        Toast.makeText(getApplicationContext(), "Buku " + judulBuku + " berhasil dihapus",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create();
        builder.show();
    }
}