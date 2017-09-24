package com.snehpandya.aad.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snehpandya.aad.R;

/**
 * Created by Sneh on 24-09-2017.
 */

public class FirstFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_object, container, false);
        ((TextView) view.findViewById(R.id.text_name)).setText(getResources().getString(R.string.drawer_open));
        return view;
    }
}
