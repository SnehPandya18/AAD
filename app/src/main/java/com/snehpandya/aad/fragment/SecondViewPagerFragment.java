package com.snehpandya.aad.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snehpandya.aad.R;
import com.snehpandya.aad.databinding.ViewpagerFragmentBinding;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class SecondViewPagerFragment extends Fragment {

    private ViewpagerFragmentBinding mViewpagerFragmentBinding;
    private Date mDate = new Date();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewpagerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.viewpager_fragment, container, false);
        mViewpagerFragmentBinding.textViewpager.setText(DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault()).format(mDate.getTime()));
        return mViewpagerFragmentBinding.getRoot();
    }
}
