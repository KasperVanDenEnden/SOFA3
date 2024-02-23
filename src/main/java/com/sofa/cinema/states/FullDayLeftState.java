package com.sofa.cinema.states;

import java.util.logging.Logger;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;
import com.sofa.cinema.template.StateMessage;
import com.sofa.cinema.template.StateMessageTemplate;

import java.util.Observable;
import java.util.Observer;

public class FullDayLeftState extends StateMessageTemplate implements IOrderState, Observer {
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
//         this.order.set_previousState(this);
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

//         this.order.set_previousState(this);
        this.order.set_currentState(new FullyProcessedState(this.order));
        logger.info("This order has already a placed reservation");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

//         this.order.set_previousState(this);
        this.order.set_currentState(new CancelState(this.order));
        // send reservation message and cancel message, WRONG
        logger.info("Order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
//         this.order.set_previousState(this);
        this.order.set_currentState(new HalfDayLeftState(this.order));
        logger.info("There are only 12 hours left until screening!");
    }

    @Override
    public void update(Observable o, Object arg) {
        this.write();
    }

    @Override
    public void write() {
         String messageConcatenation = "Hello, there is only a day left until the screening!: \n"
                         + "Name: " + this.order.getPerson().getName()  + ".\n"
                         + "Email: " + this.order.getPerson().getEmail() + ".\n"
                         + "phoneNumber: " + this.order.getPerson().getPhoneNumber() + ".\n"
                         + "------------------------------------------------- \n"
                         + "Your order,  " + this.order.getOrderNr() + ": \n"
                         + "Movie: " + this.order.getOneTicket().getMovieScreening().getMovie().toString() + "\n"
                         + "Date/Time: " + this.order.getOneTicket().getDateAndTime() + "\n" ;

        StateMessage message = new StateMessage(messageConcatenation);
        order.sendMessage(message);
    }
}
