package com.sofa.cinema.states;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;
import com.sofa.cinema.template.StateMessage;
import com.sofa.cinema.template.StateMessageTemplate;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public class CancelState extends StateMessageTemplate implements IOrderState, Observer {
        private Order order;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CancelState(Order order) {
        this.order = order;
        order.addObserver(this);
    }

    @Override
    public void addTicket(MovieTicket ticket) {
        logger.info("Can't add ticket, because order is cancelled!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        logger.info("Can't remove ticket, because order is cancelled!");
    }

    @Override
    public void editReservation() {
        logger.info("Can't edit reservation, because order is cancelled!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order is cancelled!");
    }

    @Override
    public void payOrder() {
        logger.info("Can't pay order, because order is cancelled!");
    }

    @Override
    public void cancelOrder() {
        logger.info("Can't cancel order, because order is cancelled!");
    }

    @Override
    public void ignorePayment() {
        logger.info("Can't ignore payment, because order is cancelled!");
    }

     @Override
     public void update(Observable o, Object arg) {
         this.write();
     }

    @Override
    public void write() {
         String messageConcatenation = "Hello, your order has been canceled!: \n"
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
