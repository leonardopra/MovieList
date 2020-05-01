package com.example.movielist;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestioneMovieDB {

    public static Film[] DataFromJson(Context context, String json) throws JSONException {

        final String TMDB_BASE_URL = "https://image.tmdb.org/t/p/";
        final String TMDB_POSTER_SIZE = "w500";
        final String TMDB_RESULTS = "results";
        final String TMDB_POSTER_PATH = "poster_path";
        final String TMDB_TITLE = "title";


        JSONObject movieJson = new JSONObject(json);

        JSONArray movieArray = movieJson.getJSONArray(TMDB_RESULTS);

        Film[] movieResults = new Film[movieArray.length()];


        for (int i = 0; i < movieArray.length(); i++){
            String poster_path, title, vote_average, overview, release_date;

            Film film = new Film();

            poster_path = movieArray.getJSONObject(i).optString(TMDB_POSTER_PATH);
            title = movieArray.getJSONObject(i).optString(TMDB_TITLE);
            film.setPoster(TMDB_BASE_URL + TMDB_POSTER_SIZE + poster_path);
            film.setTitle(title);


            movieResults[i] = film;
        }

        return movieResults;
    }
}