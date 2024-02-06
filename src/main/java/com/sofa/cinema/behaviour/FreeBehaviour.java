package com.sofa.cinema.behaviour;

import com.sofa.cinema.MovieTicket;

public class FreeBehaviour implements PriceRuleBehaviour {
    private int ticketIndex;
    private boolean isStudentOrder;
    private MovieTicket ticket;

    public FreeBehaviour(int ticketIndex, boolean isStudentOrder, MovieTicket ticket) {
        this.ticketIndex = ticketIndex;
        this.isStudentOrder = isStudentOrder;
        this.ticket = ticket;
    }

    @Override
    public double getPriceRule() {
        int dayNumber = ticket.getDateAndTime().getDayOfWeek().getValue();
         if (((ticketIndex + 1.0) % 2 == 0) && (isStudentOrder || (dayNumber >= 1 && dayNumber <= 4))) {
            // Free when second ticket and student or when second ticket and mo/tue/we/thu
            return 0;
        }
        return ticket.getPrice();
    }
}
