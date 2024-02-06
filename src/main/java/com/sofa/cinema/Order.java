package com.sofa.cinema;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sofa.cinema.behaviour.*;
import com.sofa.cinema.errors.ExportException;

import java.util.ArrayList;
import java.util.logging.Logger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private ArrayList<MovieTicket> movieTickets;

    private PriceRuleBehaviour priceRuleBehaviourPremium;
    private PriceRuleBehaviour priceRuleBehaviourDiscount;
    private PriceRuleBehaviour priceRuleBehaviourFree;

    // Create a logger for the Order class
    private static final Logger logger = Logger.getLogger("ORDER");

    public Order(int orderNr, boolean isStudentOrder) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
        this.movieTickets = new ArrayList<MovieTicket>();

        priceRuleBehaviourPremium = null;
        priceRuleBehaviourDiscount = null;
        priceRuleBehaviourFree = null;
    }

    public int getOrderNr() {
        return this.orderNr;
    }

    public boolean isStudentOrder() {
        return isStudentOrder;
    }

    public ArrayList<MovieTicket> getMovieTickets() {
        return movieTickets;
    }

    public void addSeatReservation(MovieTicket ticket) {
        this.movieTickets.add(ticket);
    }

    public void export(ExportBehaviour behaviour) throws ExportException {
        behaviour.export(this);
    }

    public void setPremiumFeeBehaviour(MovieTicket ticket, boolean isPremium, boolean isStudentOrder) {
        this.priceRuleBehaviourPremium = new PremiumFeeBehaviour(ticket, isPremium, isStudentOrder);
    }

    public void setDiscountBehaviour(int totalTickets, MovieTicket ticket, double ticketPrice, boolean isStudentOrder) {
        int dayNumber = ticket.getDateAndTime().getDayOfWeek().getValue();
        this.priceRuleBehaviourDiscount = new DiscountBehaviour(totalTickets, dayNumber, ticketPrice, isStudentOrder);
    }

    public void setFreeBehaviour(int ticketIndex, boolean isStudentOrder, MovieTicket ticket) {
        this.priceRuleBehaviourFree = new FreeBehaviour(ticketIndex, isStudentOrder, ticket);
    }

    public double calculatePrice() {
        double totalPrice = 0;

        for (int i = 0; i < movieTickets.size(); i++) {
            MovieTicket ticket = movieTickets.get(i);

            this.setFreeBehaviour(i, isStudentOrder, ticket);
            double amount = this.priceRuleBehaviourFree.getPriceRule();
            if (amount == 0) continue;

            boolean isPremium = ticket.isPremiumTicket();

            this.setPremiumFeeBehaviour(ticket, isPremium, isStudentOrder);
            double ticketPrice = this.priceRuleBehaviourPremium.getPriceRule();

            this.setDiscountBehaviour(movieTickets.size(), ticket, ticketPrice, isStudentOrder);
            ticketPrice = this.priceRuleBehaviourDiscount.getPriceRule();

            totalPrice += ticketPrice;
        }

        return totalPrice;
    }
}
