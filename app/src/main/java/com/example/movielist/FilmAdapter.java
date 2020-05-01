package com.example.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmAdapterViewHolder> {

    private Film[] mMovieData;
    private final FilmAdapterOnClickHandler mClickHandler;

    public FilmAdapter(Film[] movie, FilmAdapterOnClickHandler clickHandler) {
        mMovieData = movie;
        mClickHandler = clickHandler;
    }

    public interface FilmAdapterOnClickHandler {
        void onClick(int adapterPosition);
    }

    public class FilmAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMovieListImageView;

        public FilmAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieListImageView = (ImageView) itemView.findViewById(R.id.poster_film);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }
    }

    @NonNull
    @Override
    public FilmAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.righe_film;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new FilmAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapterViewHolder holder, int position) {
        String movieToBind = mMovieData[position].getPoster();
        Picasso.get()
                .load(movieToBind)
                .into(holder.mMovieListImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) {
            return 0;
        }
        return mMovieData.length;
    }
}
