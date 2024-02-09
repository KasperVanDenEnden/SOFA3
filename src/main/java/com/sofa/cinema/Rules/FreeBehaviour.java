package com.sofa.cinema.Rules;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class FreeBehaviour implements PriceRuleBehaviour {
    public double getPriceRule(double currentPrice, int ticketOrder, MovieTicket ticket, Order order) {
        int dayNumber = ticket.getDateAndTime().getDayOfWeek().getValue();
         if (((ticketOrder) % 2 == 0) && (order.isStudentOrder() || (dayNumber >= 1 && dayNumber <= 4))) {
            // Free when second ticket and student or when second ticket and mo/tue/we/thu
            return 0;
        }
        return currentPrice;
    }
}
