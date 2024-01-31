package com.sofa.cinema;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

public class Movie {
    private String title;

    private List<MovieScreening> screenings;

    public Movie(String title) {
        this.title = title;
    }

    public void addScreening(MovieScreening screening) {
        this.screenings.add(screening);
    }

    public String toString() {
        return this.title.toString();
    }


}
