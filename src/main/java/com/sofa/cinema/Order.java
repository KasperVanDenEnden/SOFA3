package com.sofa.cinema;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sofa.cinema.Rules.*;
import com.sofa.cinema.errors.ExportException;
import com.sofa.cinema.exports.ExportBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private ArrayList<MovieTicket> movieTickets;

    private List<PriceRuleBehaviour> ticketPriceRules;

    // Create a logger for the Order class
    private static final Logger logger = Logger.getLogger("ORDER");

    public Order(int orderNr, boolean isStudentOrder, List<PriceRuleBehaviour> ticketPriceRules) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
        this.movieTickets = new ArrayList<MovieTicket>();
        this.ticketPriceRules = ticketPriceRules;
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

    public double calculatePrice() {
        double totalPrice = 0;

        for (int i = 0; i < movieTickets.size(); i++) {
            MovieTicket ticket = movieTickets.get(i);
            var ticketPrice = ticket.getPrice();

            for (PriceRuleBehaviour priceRule : ticketPriceRules) {
                // Your code to process each PriceRuleBehaviour instance
                if(ticketPrice > 0.0)
                    ticketPrice = priceRule.getPriceRule(ticketPrice,i,ticket,this);
            }
            totalPrice += ticketPrice;
        }

        return totalPrice;
    }
}
