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
import com.progmob_d_kelompok_8.biblio.model.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ListViewHolder> {

    private ArrayList<User> listUser;
    private UserListAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(UserListAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public UserListAdapter(ArrayList<User> list) {
        this.listUser = list;
    }

    public void setFilteredList(ArrayList<User> filteredList){
        this.listUser = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_user, parent, false);
        return new UserListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListAdapter.ListViewHolder holder, int position) {
        User user = listUser.get(position);

        byte[] image = user.getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);

        holder.imgPhoto.setImageBitmap(bitmap);
        holder.tvNama.setText(user.getNama());
        holder.tvRole.setText(user.getRole());
        holder.tvEmail.setText(user.getEmail());

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listUser.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvNama, tvRole, tvEmail;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.iv_gambar_pengguna);
            tvNama = (TextView) itemView.findViewById(R.id.tv_item_nama);
            tvRole = (TextView) itemView.findViewById(R.id.tv_item_role);
            tvEmail = (TextView) itemView.findViewById(R.id.tv_item_email);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(User data);
    }
}
