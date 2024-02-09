package com.sofa.cinema.Rules;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class PremiumFeeBehaviour implements PriceRuleBehaviour {

    private final double SURCHARGE_STUDENTS = 2.0;
    private final double SURCHARGE_NON_STUDENTS = 3.0;

    public double getPriceRule(double currentPrice, int ticketOrder, MovieTicket ticket, Order order) {
        if (!ticket.isPremiumTicket()) return currentPrice;

        return currentPrice +( order.isStudentOrder() ? SURCHARGE_STUDENTS : SURCHARGE_NON_STUDENTS);
    }
}
