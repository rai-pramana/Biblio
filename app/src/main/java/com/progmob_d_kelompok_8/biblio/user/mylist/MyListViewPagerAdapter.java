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
                return new CompletedFragment();
            case 1:
                return new PlanToReadFragment();
            case 2:
                return new ReadingFragment();
            case 3:
                return new DroppedFragment();
            case 4:
                return new FavoriteFragment();
            default:
                return new CompletedFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
