package com.derek.paginationtest;

import java.util.ArrayList;

/**
 * Created by Michael on 8/1/2017.
 */

public class Movie {

    private String title;

    public Movie(){

    }

    public Movie(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<Movie> createMovies(int itemCount){
        ArrayList<Movie> movies = new ArrayList<>();
        int count;

        for(int i = 0;i < 10;i++){
            count = itemCount == 0 ? (itemCount + 1 + i):(itemCount + i);
            Movie movie = new Movie("Movie " + count);
            movies.add(movie);
        }
        return movies;
    }
}
