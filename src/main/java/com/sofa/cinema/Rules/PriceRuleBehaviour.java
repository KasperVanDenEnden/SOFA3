package com.sofa.cinema.Rules;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public interface PriceRuleBehaviour {
    double getPriceRule(double currentPrice, int ticketOrder, MovieTicket ticket, Order order);
}
