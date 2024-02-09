package com.sofa.cinema.states;

import java.util.logging.Logger;
import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class HasTicketsState implements IOrderState {
    private Order order;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public HasTicketsState(Order order) {
        this.order = order;
    }

    @Override
    public void addTicket(MovieTicket ticket) {
        this.order.addTicketToList(ticket);

        logger.info("The ticket had been added to the order!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        this.order.removeTicketFromList(ticket);

        // If no Tickets? Go back to noTicketState
        if (this.order.getMovieticketSize() == 0) {
            order.set_currentState(this.order.get_previousState());
            order.set_previousState(this);
        }
        logger.info("The ticket had been removed from the order!");
    }

    @Override
    public void editReservation() {
        logger.info("Can't edit reservation, because order has not been reserved!");
    }

    @Override
    public void placeReservation() {
        this.order.setSubmitted(true);
        this.order.setPayed(false);

        this.order.set_previousState(this);
        this.order.set_currentState(new PlacedReservationState(this.order));

        logger.info("The reservation has been placed according to this order!");
    }

    @Override
    public void payOrder() {
        logger.info("Can't pay order, because order has not been reserved!");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

        this.order.set_previousState(this);
        this.order.set_currentState(new CancelState());

        logger.info("This order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
        logger.info("Can't ignore payment, because order has not been reserved!");
    }
}
