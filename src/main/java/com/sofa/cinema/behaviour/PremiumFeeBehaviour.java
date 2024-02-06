package com.sofa.cinema.behaviour;

import com.sofa.cinema.MovieTicket;

public class PremiumFeeBehaviour implements PriceRuleBehaviour {
    private MovieTicket ticket;
    private boolean isPremium;
    private boolean isStudentOrder;

    public PremiumFeeBehaviour(MovieTicket ticket, boolean isPremium, boolean isStudentOrder) {
        this.ticket = ticket;
        this.isPremium = isPremium;
        this.isStudentOrder = isStudentOrder;
    }

    @Override
    public double getPriceRule() {
        double ticketPrice = ticket.getPrice();
        if (isPremium) ticketPrice += isStudentOrder ? 2.0 : 3.0;
        return ticketPrice;
    }
}
