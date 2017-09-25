package com.snehpandya.aad.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snehpandya.aad.R;
import com.snehpandya.aad.adapter.MoviePagerAdapter;
import com.snehpandya.aad.databinding.FragmentThreeBinding;

/**
 * Created by Sneh on 24-09-2017.
 */

public class ThirdFragment extends Fragment {

    private FragmentThreeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_three, container, false);

        mBinding.viewpager.setAdapter(new MoviePagerAdapter(getChildFragmentManager(), getContext()));

        mBinding.tabs.setupWithViewPager(mBinding.viewpager);
        mBinding.tabs.getTabAt(0).setText("One");
        mBinding.tabs.getTabAt(1).setText("Two");
        mBinding.tabs.getTabAt(2).setText("Three");

        return mBinding.getRoot();
    }
}
