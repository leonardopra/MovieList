package com.example.movielist;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GestioneRichiesteWeb {
    final static String THEMOVIEDB_BASE_URL = "https://api.themoviedb.org/3/movie/";
    final static String PARAM_API_KEY = "api_key";
    final static String apiKey = "a94f03c2394b29d082298434301034a1";
    final static String PARAM_LANGUAGE = "language";
    final static String language = "en-US";

    public static URL buildUrl(String theMovieDbSearchQuery){
        Uri builtUri = Uri.parse(THEMOVIEDB_BASE_URL).buildUpon()
                .appendEncodedPath(theMovieDbSearchQuery)
                .appendQueryParameter(PARAM_API_KEY, apiKey)
                .appendQueryParameter(PARAM_LANGUAGE, language)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}