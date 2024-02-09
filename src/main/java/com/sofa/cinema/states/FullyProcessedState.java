package com.sofa.cinema.states;

import java.util.logging.Logger;

import com.sofa.cinema.MovieTicket;

public class FullyProcessedState implements IOrderState {
    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void addTicket(MovieTicket ticket) {
        logger.info("Can't add ticket, because order is fully processed!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        logger.info("Can't remove ticket, because order is fully processed!");
    }

    @Override
    public void editReservation() {
        logger.info("Can't edit reservation, because order is fully processed!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order is fully processed!");
    }

    @Override
    public void payOrder() {
        logger.info("Can't pay order, because order is fully processed!");
    }

    @Override
    public void cancelOrder() {
        logger.info("Can't cancel order, because order is fully processed!");
    }

    @Override
    public void ignorePayment() {
        logger.info("Can't ignore payment, because order is fully processed!");
    }
}
