package com.snehpandya.aad.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.snehpandya.aad.fragment.FirstViewPagerFragment;
import com.snehpandya.aad.fragment.SecondViewPagerFragment;
import com.snehpandya.aad.fragment.ThirdViewPagerFragment;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private Context context;

    public MoviePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstViewPagerFragment();
            case 1:
                return new SecondViewPagerFragment();
            case 2:
                return new ThirdViewPagerFragment();
            default: return new FirstViewPagerFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
