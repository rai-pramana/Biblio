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
import com.progmob_d_kelompok_8.biblio.model.ReviewedBook;

import java.util.ArrayList;

public class BookReviewedAdapter extends RecyclerView.Adapter<BookReviewedAdapter.ListViewHolder> {

    private ArrayList<ReviewedBook> listReviewedBook;
    private BookReviewedAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(BookReviewedAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public BookReviewedAdapter(ArrayList<ReviewedBook> list) {
        this.listReviewedBook = list;
    }

    @NonNull
    @Override
    public BookReviewedAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_book_reviewed, parent, false);
        return new BookReviewedAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookReviewedAdapter.ListViewHolder holder, int position) {
        ReviewedBook reviewedBook = listReviewedBook.get(position);

        byte[] image = reviewedBook.getGambarSampul();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        if (reviewedBook.getTglMulai().isEmpty()) {
            holder.lbTglMulai.setVisibility(View.GONE);
            holder.tvTglMulai.setVisibility(View.GONE);
        } else {
            holder.lbTglMulai.setVisibility(View.VISIBLE);
            holder.tvTglMulai.setVisibility(View.VISIBLE);
            holder.tvTglMulai.setText(reviewedBook.getTglMulai());
            holder.lbTglMulai.setText("Mulai:");
        }

        if (reviewedBook.getReview().isEmpty()) {
            holder.tvReview.setVisibility(View.GONE);
        } else {
            holder.tvReview.setVisibility(View.VISIBLE);
            holder.tvReview.setText(reviewedBook.getReview());
        }

        if (reviewedBook.getSkorPengguna() <= 0.0) {
            holder.tvSkor.setVisibility(View.GONE);
            holder.lbSkor.setVisibility(View.GONE);
            holder.ivSkor.setVisibility(View.GONE);
        } else {
            holder.tvSkor.setVisibility(View.VISIBLE);
            holder.lbSkor.setVisibility(View.VISIBLE);
            holder.ivSkor.setVisibility(View.VISIBLE);
            holder.tvSkor.setText(String.valueOf((int) reviewedBook.getSkorPengguna()));
            holder.lbSkor.setText("/10");
        }

        holder.imgPhoto.setImageBitmap(bitmap);
        holder.tvJudul.setText(reviewedBook.getJudulBuku());
        holder.tvTglTerbit.setText(reviewedBook.getTglTerbit());
        holder.tvTglSelesai.setText(reviewedBook.getTglSelesai());
        holder.lbTglSelesai.setText("Selesai:");

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listReviewedBook.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listReviewedBook.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, ivSkor;
        TextView tvJudul, tvTglTerbit, tvTglMulai, tvTglSelesai, tvSkor, tvReview, lbTglMulai, lbTglSelesai, lbSkor;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.iv_gambar_sampul);
            tvJudul = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvTglTerbit = (TextView) itemView.findViewById(R.id.tv_item_tahun);
            tvTglMulai = (TextView) itemView.findViewById(R.id.tv_item_tanggal_mulai);
            tvTglSelesai = (TextView) itemView.findViewById(R.id.tv_item_tanggal_selesai);
            tvSkor = (TextView) itemView.findViewById(R.id.tv_item_rating);
            tvReview = (TextView) itemView.findViewById(R.id.tv_item_review);
            lbTglMulai = (TextView) itemView.findViewById(R.id.label_tanggal_mulai);
            lbTglSelesai = (TextView) itemView.findViewById(R.id.label_tanggal_selesai);
            lbSkor = (TextView) itemView.findViewById(R.id.label_rating);
            ivSkor = (ImageView) itemView.findViewById(R.id.iv_rating);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ReviewedBook data);
    }
}
