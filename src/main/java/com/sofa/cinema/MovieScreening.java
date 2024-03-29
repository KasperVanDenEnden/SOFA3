package com.sofa.cinema;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieScreening {
    private LocalDateTime dateAndTime;
    private double pricePerSeat;
    private Movie movie;
    private ArrayList<MovieTicket> tickets;

    public MovieScreening(Movie movie, LocalDateTime dateAndTime, double pricePerSeat) {
        this.dateAndTime = dateAndTime;
        this.pricePerSeat = pricePerSeat;
        this.movie = movie;
        tickets = new ArrayList<MovieTicket>();
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return dateAndTime + " " + pricePerSeat;
    }
}
