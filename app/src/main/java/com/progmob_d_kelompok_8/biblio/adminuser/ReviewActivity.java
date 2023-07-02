package com.progmob_d_kelompok_8.biblio.adminuser;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.model.Review;
import com.progmob_d_kelompok_8.biblio.tool.ReviewAdapter;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;
    private RecyclerView rvReview;
    private ArrayList<Review> list = new ArrayList<>();
    private ReviewAdapter listReviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        session = new Session(ReviewActivity.this);
        db = new DatabaseHelper(ReviewActivity.this);

        setTitle("Review Buku " + session.getBookTitle());

        rvReview = findViewById(R.id.recyclerViewReview);
        rvReview.setHasFixedSize(true);

        list.addAll(getListReview());
        showRecyclerList();

    }

    @Override
    public void onResume() {
        super.onResume();

        list.clear();
        list.addAll(getListReview());
        showRecyclerList();
        listReviewAdapter.notifyDataSetChanged();
    }

    public ArrayList<Review> getListReview() {
        Cursor cursor = db.getReviewBook(session.getBookIdDetail());

        ArrayList<Review> listReview = new ArrayList<>();
        while (cursor.moveToNext()){
            int idPengguna = cursor.getInt(0);
            String nama = cursor.getString(1);
            String tglReview = cursor.getString(2);
            String review = cursor.getString(3);
            float skorPengguna = cursor.getFloat(4);
            byte[] foto = cursor.getBlob(5);

            Review reviews = new Review(idPengguna
                    , nama
                    , tglReview
                    , review
                    , skorPengguna
                    , foto);

            listReview.add(reviews);
        }

        cursor.close();
        db.close();

        return listReview;
    }

    private void moveDetail(Review reviews) {
        session.setUserIdDetail(reviews.getIdPengguna());
        startActivity(new Intent(ReviewActivity.this, DetailUserActivity.class));
    }

    private void showRecyclerList() {
        rvReview.setLayoutManager(new LinearLayoutManager(ReviewActivity.this));
        listReviewAdapter = new ReviewAdapter(list);
        rvReview.setAdapter(listReviewAdapter);

        listReviewAdapter.setOnItemClickCallback(data -> {
            moveDetail(data);
        });
    }

}