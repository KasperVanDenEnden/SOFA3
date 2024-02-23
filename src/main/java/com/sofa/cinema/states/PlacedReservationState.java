package com.sofa.cinema.states;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;
import com.sofa.cinema.adapter.MessageService;
import com.sofa.cinema.template.StateMessage;
import com.sofa.cinema.template.StateMessageTemplate;

public class PlacedReservationState extends StateMessageTemplate implements IOrderState, Observer  {
    private Order order;
    private MessageService message;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public PlacedReservationState(Order order) {
        this.order = order;
        order.addObserver(this);
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

//         this.order.set_previousState(this);
        this.order.set_currentState(new HasTicketsState(this.order));

        logger.info("This order will be edited!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order has been reserved!");
    }

    @Override
    public void payOrder() {
        this.order.setPayed(true);

//         this.order.set_previousState(this);
        this.order.set_currentState(new FullyProcessedState(this.order));

        logger.info("This order has been payed!");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

//         this.order.set_previousState(this);
        this.order.set_currentState(new CancelState(this.order));

        logger.info("This order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
//         this.order.set_previousState(this);

        this.order.set_currentState(new FullDayLeftState(this.order));

        logger.info("There are only 24 hours left until screening!");
    }

    @Override
    public void update(Observable o, Object arg) {
        this.write();
    }

    @Override
    public void write() {
        String messageConcatenation = "Hello, you placed an reservation under this account!: \n"
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
