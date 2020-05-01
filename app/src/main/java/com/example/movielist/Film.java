package com.example.movielist;

public class Film {
    private String title, poster;

    public Film(){
    }

    public Film(String title, String poster){
        this.title = title;
        this.poster = poster;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}