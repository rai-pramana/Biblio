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
import com.progmob_d_kelompok_8.biblio.adminuser.DetailBookActivity;
import com.progmob_d_kelompok_8.biblio.model.Book;
import com.progmob_d_kelompok_8.biblio.tool.BookListAdapter;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.tool.Session;

import java.util.ArrayList;

public class BukuFragment extends Fragment {

    private DatabaseHelper db;
    private Session session;
    private RecyclerView rvBooks;
    private ArrayList<Book> list = new ArrayList<>();
    private BookListAdapter listBookAdapter;
    private SearchView searchView;
    private FloatingActionButton add_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buku, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new Session(getActivity());
        db = new DatabaseHelper(getActivity());

        rvBooks = view.findViewById(R.id.recyclerViewBook);
        rvBooks.setHasFixedSize(true);

        searchView =  view.findViewById(R.id.searchViewBook);
        searchView.clearFocus();
        searchView.setQueryHint("Cari Buku...");

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
                startActivity(new Intent(getActivity(), AddBukuActivity.class));
            }
        });

        list.addAll(getListBooks());
        showRecyclerList();
    }

    private void filterList(String text) {
        ArrayList<Book> filteredList = new ArrayList<>();
        for (Book book : list){
            if (book.getJudulBuku().toLowerCase().contains(text.toLowerCase())
                    || book.getNamaGenre().toLowerCase().contains(text.toLowerCase())
                    || book.getNamaJenisBuku().toLowerCase().contains(text.toLowerCase())
                    || book.getNamaPenerbit().toLowerCase().contains(text.toLowerCase())
                    || book.getNamaPenulis().toLowerCase().contains(text.toLowerCase())
                    || book.getTglTerbit().toLowerCase().contains(text.toLowerCase())
            ){
                filteredList.add(book);
            }
        }

        if (filteredList.isEmpty()){
            listBookAdapter.setFilteredList(filteredList);
        } else {
            listBookAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        list.clear();
        list.addAll(getListBooks());
        showRecyclerList();
        listBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        list.clear();
    }

    public ArrayList<Book> getListBooks() {
        Cursor cursor = db.getAllBookData();

        ArrayList<Book> listBook = new ArrayList<>();
        while (cursor.moveToNext()){
            int idBuku = cursor.getInt(0);
            int id_pengguna = cursor.getInt(1);
            String namaJenisBuku = cursor.getString(2);
            String namaGenre = cursor.getString(3);
            String namaPenulis = cursor.getString(4);
            String namaPenerbit = cursor.getString(5);
            String judulBuku = cursor.getString(6);
            String tglTerbit = cursor.getString(7);
            String sinopsis = cursor.getString(8);
            float skor = cursor.getFloat(9);
            byte[] gambarSampul = cursor.getBlob(10);
            int jumlahPembaca = cursor.getInt(11);
            int peringkat  = cursor.getInt(12);

            Book book = new Book(idBuku
                    , id_pengguna
                    , namaJenisBuku
                    , namaGenre
                    , namaPenulis
                    , namaPenerbit
                    , judulBuku
                    , tglTerbit
                    , sinopsis
                    , skor
                    , gambarSampul
                    , jumlahPembaca
                    , peringkat
                    , 0);

            listBook.add(book);
        }

        cursor.close();
        db.close();

        return listBook;
    }

    private void moveDetail(Book book) {
        session.setBookIdDetail(book.getIdBuku());
        startActivity(new Intent(getActivity(), DetailBookActivity.class));
    }

    private void showRecyclerList() {
        rvBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        listBookAdapter = new BookListAdapter(list, false, false);
        rvBooks.setAdapter(listBookAdapter);

        listBookAdapter.setOnItemClickCallback(data -> {
            moveDetail(data);
        });
    }
}