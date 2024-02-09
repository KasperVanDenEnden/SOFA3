package com.sofa.cinema.states;

import com.sofa.cinema.MovieTicket;

public interface IOrderState {
    void addTicket(MovieTicket ticket);
    void removeTicket(MovieTicket ticket);
    void editReservation();
    void placeReservation();
    void payOrder();
    void cancelOrder();
    void ignorePayment();
}
