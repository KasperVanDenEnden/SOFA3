package com.sofa.cinema.states;

import java.util.logging.Logger;
import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class PlacedReservationState implements IOrderState {
    private Order order;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public PlacedReservationState(Order order) {
        this.order = order;
    }

    @Override
    public void addTicket(MovieTicket ticket) {
        logger.info("Can't add ticket, because order has been reserved!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        logger.info("Can't remove ticket, because order has been reserved!");
    }

    @Override
    public void editReservation() {
        this.order.setSubmitted(false);

        this.order.set_currentState(this.order.get_previousState());
        this.order.set_previousState(this);

        logger.info("This order will be edited!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order has been reserved!");
    }

    @Override
    public void payOrder() {
        this.order.setPayed(true);

        this.order.set_previousState(this);
        this.order.set_currentState(new FullyProcessedState());

        logger.info("This order has been payed!");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

        this.order.set_currentState(new CancelState());
        this.order.set_previousState(this);

        logger.info("This order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
        this.order.set_previousState(this);
        this.order.set_currentState(new FullDayLeftState(this.order));

        logger.info("There are only 24 hours left until screening!");
    }
}
