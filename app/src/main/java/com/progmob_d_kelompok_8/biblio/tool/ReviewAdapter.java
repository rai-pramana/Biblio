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
import com.progmob_d_kelompok_8.biblio.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ListViewHolder> {

    private ArrayList<Review> listReview;
    private ReviewAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(ReviewAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ReviewAdapter(ArrayList<Review> list) {
        this.listReview = list;
    }

    @NonNull
    @Override
    public ReviewAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_review, parent, false);
        return new ReviewAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ListViewHolder holder, int position) {
        Review review = listReview.get(position);

        byte[] image = review.getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        holder.imgPhoto.setImageBitmap(bitmap);
        holder.tvNama.setText(review.getNama());
        holder.tvSkor.setText(String.valueOf((int) review.getSkorPengguna()));
//        holder.lbSkor
        holder.tvTanggal.setText(review.getTglReview());
        holder.tvReview.setText(review.getReview());
//        holder.ivSkor
//        holder.ivTanggal

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listReview.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, ivSkor, ivTanggal;
        TextView tvNama, tvSkor, lbSkor, tvTanggal, tvReview;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = (TextView) itemView.findViewById(R.id.tv_item_nama);
            tvSkor = (TextView) itemView.findViewById(R.id.tv_item_rating_personal);
            lbSkor = (TextView) itemView.findViewById(R.id.label_rating);
            tvTanggal = (TextView) itemView.findViewById(R.id.tv_item_tanggal);
            tvReview = (TextView) itemView.findViewById(R.id.tv_item_review);
            imgPhoto = (ImageView) itemView.findViewById(R.id.iv_foto_profil);
            ivSkor = (ImageView) itemView.findViewById(R.id.iv_rating);
            ivTanggal = (ImageView) itemView.findViewById(R.id.iv_tanggal);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Review data);
    }
}
