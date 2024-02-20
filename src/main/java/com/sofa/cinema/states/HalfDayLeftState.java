package com.sofa.cinema.states;
import java.util.logging.Logger;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class HalfDayLeftState implements IOrderState {
    private Order order;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public HalfDayLeftState(Order order) {
        this.order = order;
    }

    @Override
    public void addTicket(MovieTicket ticket) {
        logger.info("Can't add ticket, because order payment has not been received on time!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        logger.info("Can't remove ticket, because order payment has not been received on time!");
    }

    @Override
    public void editReservation() {
        logger.info("Can't edit reservation, because order payment has not been received on time!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order payment has not been received on time!");
    }

    @Override
    public void payOrder() {
        logger.info("Can't pay order, because order payment has not been received on time!");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

        this.order.set_currentState(new CancelState(this.order));
        this.order.set_previousState(this);

        logger.info("This order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
        this.cancelOrder();

        logger.info("Time is up to pay for this order!");
    }
}
