package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.net.URL;

public class MainActivity extends AppCompatActivity implements FilmAdapter.FilmAdapterOnClickHandler {

    public RecyclerView MyRecyclerView;
    public FilmAdapter MyFilmAdapter;
    public Film[] jsonMovieData;

    String query = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);


        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setHasFixedSize(true);
        MyRecyclerView.setAdapter(MyFilmAdapter);

        loadMovieData();
    }

    private void loadMovieData() {
        String theMovieDbQueryType = query;
        MyRecyclerView.setVisibility(View.VISIBLE);
        new FetchMovieTask().execute(theMovieDbQueryType);
    }

    @Override
    public void onClick(int adapterPosition) {

    }
    public class FetchMovieTask extends AsyncTask<String, Void, Film[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Film[] doInBackground(String... params) {
            if (params.length == 0){
                return null;
            }

            String sortBy = params[0];
            URL movieRequestUrl = GestioneRichiesteWeb.buildUrl(sortBy);

            try {
                String jsonMovieResponse = GestioneRichiesteWeb.getResponseFromHttpUrl(movieRequestUrl);

                jsonMovieData
                        = GestioneMovieDB.DataFromJson(MainActivity.this, jsonMovieResponse);

                return jsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Film[] movieData) {
            if (movieData != null) {
                MyRecyclerView.setVisibility(View.VISIBLE);
                MyFilmAdapter = new FilmAdapter(movieData,MainActivity.this);
                MyRecyclerView.setAdapter(MyFilmAdapter);
            } else {
                Log.i("errore", "errore ");

            }
        }

    }


}
