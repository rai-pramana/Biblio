package com.progmob_d_kelompok_8.biblio.user.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeViewPagerAdapter extends FragmentStateAdapter {
    public HomeViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new RankBookFragment();
            case 1:
                return new MostPopularFragment();
            default:
                return new RankBookFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
