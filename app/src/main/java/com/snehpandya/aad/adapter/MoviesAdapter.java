package com.snehpandya.aad.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.snehpandya.aad.R;
import com.snehpandya.aad.databinding.RecyclerListItemBinding;
import com.snehpandya.aad.model.Result;

import java.util.List;

/**
 * Created by sneh.pandya on 25/09/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Result> mResults;

    public MoviesAdapter(List<Result> results) {
        this.mResults = results;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_list_item, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MovieViewHolder holder, final int position) {
        holder.bindResult(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private RecyclerListItemBinding binding;

        MovieViewHolder(RecyclerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindResult(Result result) {
            binding.setResult(result);
            binding.executePendingBindings();
        }
    }
}
