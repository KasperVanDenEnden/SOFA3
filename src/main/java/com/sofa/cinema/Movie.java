package com.sofa.cinema;

import java.util.ArrayList;

public class Movie {
    private String title;
    private ArrayList<MovieScreening> screenings;

    public Movie(String title) {
        this.title = title;
        screenings = new ArrayList<MovieScreening>();
    }

    public void addScreening(MovieScreening screening) {
        this.screenings.add(screening);
    }

    public String toString() {
        return this.title.toString();
    }


}
