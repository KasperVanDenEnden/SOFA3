package com.sofa.cinema.Rules;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class DiscountBehaviour implements PriceRuleBehaviour {
    public double getPriceRule(double currentPrice, int ticketOrder, MovieTicket ticket, Order order) {
        int dayNumber = ticket.getDateAndTime().getDayOfWeek().getValue();

        return dayNumber >= 5 && dayNumber <= 7 && !order.isStudentOrder() && order.getMovieTickets().size() >= 6 ? currentPrice * 0.9 : currentPrice;
    }
}
