package com.progmob_d_kelompok_8.biblio.user.mylist;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyListViewPagerAdapter extends FragmentStateAdapter {
    public MyListViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SelesaiFragment();
            case 1:
                return new RencanaDibacaFragment();
            case 2:
                return new SedangDibacaFragment();
            case 3:
                return new DijatuhkanFragment();
            case 4:
                return new FavoritFragment();
            default:
                return new SelesaiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
