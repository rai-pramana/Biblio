package com.progmob_d_kelompok_8.biblio.user.MyListFragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.adminuser.DetailBookActivity;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.model.ReviewedBook;
import com.progmob_d_kelompok_8.biblio.tool.BookReviewedAdapter;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.util.ArrayList;

public class SelesaiFragment extends Fragment {

    private DatabaseHelper db;
    private Session session;
    private RecyclerView rvBooks;
    private ArrayList<ReviewedBook> list = new ArrayList<>();
    private BookReviewedAdapter listReviewedBookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selesai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new Session(getActivity());
        db = new DatabaseHelper(getActivity());

        rvBooks = view.findViewById(R.id.recyclerViewBook);
        rvBooks.setHasFixedSize(true);

        list.addAll(getListReviewedBooks());
        showRecyclerList();
    }

    @Override
    public void onResume() {
        super.onResume();

        list.clear();
        list.addAll(getListReviewedBooks());
        showRecyclerList();
        listReviewedBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        list.clear();
    }

    public ArrayList<ReviewedBook> getListReviewedBooks() {
        Cursor cursor = db.getReviewedBookData(session.getUserId(), "Selesai");

        ArrayList<ReviewedBook> listReviewedBook = new ArrayList<>();
        while (cursor.moveToNext()){
            int idBuku = cursor.getInt(0);
            String judulBuku = cursor.getString(1);
            String tglTerbit = cursor.getString(2);
            String tglMulai = cursor.getString(3);
            String tglSelesai = cursor.getString(4);
            String review = cursor.getString(5);
            float skorPengguna = cursor.getFloat(6);
            byte[] gambarSampul = cursor.getBlob(7);

            ReviewedBook reviewedBook = new ReviewedBook(idBuku
                    , judulBuku
                    , tglTerbit.substring(0,4)
                    , tglMulai
                    , tglSelesai
                    , review
                    , skorPengguna
                    , gambarSampul);

            listReviewedBook.add(reviewedBook);
        }

        cursor.close();
        db.close();

        return listReviewedBook;
    }

    private void moveDetail(ReviewedBook reviewedBook) {
        session.setBookIdDetail(reviewedBook.getIdBuku());
        startActivity(new Intent(getActivity(), DetailBookActivity.class));
    }

    private void showRecyclerList() {
        rvBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        listReviewedBookAdapter = new BookReviewedAdapter(list);
        rvBooks.setAdapter(listReviewedBookAdapter);

        listReviewedBookAdapter.setOnItemClickCallback(data -> {
            moveDetail(data);
        });
    }
}