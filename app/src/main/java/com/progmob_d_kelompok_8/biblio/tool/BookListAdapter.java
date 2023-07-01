package com.progmob_d_kelompok_8.biblio.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.model.Book;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ListViewHolder> {

    private ArrayList<Book> listBook;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public BookListAdapter(ArrayList<Book> list) {
        this.listBook = list;
    }

    public void setFilteredList(ArrayList<Book> filteredList){
        this.listBook = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_book, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Book book = listBook.get(position);

        byte[] image = book.getGambarSampul();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        holder.imgPhoto.setImageBitmap(bitmap);
        holder.tvJudul.setText(book.getJudulBuku());
        holder.tvSkor.setText(String.valueOf(book.getSkor()));
        holder.tvPembaca.setText(String.valueOf(book.getJumlahPembaca()));
        holder.tvTahun.setText(book.getTglTerbit());
        holder.tvSinopsis.setText(book.getSinopsis());

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listBook.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvSkor, tvSinopsis, tvPembaca, tvTahun;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.iv_gambar_sampul);
            tvJudul = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvSkor = (TextView) itemView.findViewById(R.id.tv_item_rating);
            tvPembaca = (TextView) itemView.findViewById(R.id.tv_item_pembaca);
            tvTahun = (TextView) itemView.findViewById(R.id.tv_item_tahun);
            tvSinopsis = (TextView) itemView.findViewById(R.id.tv_item_sinopsis);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Book data);
    }
}
