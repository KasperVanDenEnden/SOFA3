package com.sofa.cinema.states;

import java.util.logging.Logger;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

import java.util.Observable;
import java.util.Observer;

public class FullDayLeftState implements IOrderState, Observer {
    private Order order;

    final Logger logger = Logger.getLogger(this.getClass().getName());

    public FullDayLeftState(Order order) {
        this.order = order;
    }

    @Override
    public void addTicket(MovieTicket ticket) {
        logger.info("Can't add ticket, because order is submitted!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        logger.info("Can't remove ticket, because order is submitted!");
    }

    @Override
    public void editReservation() {
        this.order.setSubmitted(false);

        this.order.set_currentState(new HasTicketsState(this.order));
        this.order.set_previousState(this);

        logger.info("Can't edit reservation, because order has been submitted!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order has been submitted!");
    }

    @Override
    public void payOrder() {
        this.order.setPayed(true);

        logger.info("Order has been payed");

        this.order.set_previousState(this);
        this.order.set_currentState(new FullyProcessedState(this.order));

        logger.info("This order has already a placed reservation");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

        this.order.set_previousState(this);
        this.order.set_currentState(new CancelState(this.order));

        logger.info("Order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
        this.order.set_previousState(this);
        this.order.set_currentState(new HalfDayLeftState(this.order));

        logger.info("There are only 12 hours left until screening!");
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) o;
        order.sendMessage();
    }
}
