package com.sofa.cinema;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieTicket {
    private Integer rowNr;
    private Integer seatNr;
    private Boolean isPremium;
    private MovieScreening screening;

    public MovieTicket(MovieScreening movieScreening, Boolean isPremiumReservation, Integer seatRow, Integer seatNr) {
        this.rowNr = seatRow;
        this.seatNr = seatNr;
        this.isPremium = isPremiumReservation;
        this.screening = movieScreening;
    }

    public Boolean isPremiumTicket() {
        return isPremium;
    }

    public double getPrice() {
        return this.screening.getPricePerSeat();
    }

    public LocalDateTime getDateAndTime() {
        return this.screening.getDateAndTime();
    }

    public MovieScreening getMovieScreening() {
        return this.screening;
    }

    public String toString() {
        return "Row: " + rowNr + "& Seat: " + seatNr + "(Premium: " + isPremium + ")";
    }
}
