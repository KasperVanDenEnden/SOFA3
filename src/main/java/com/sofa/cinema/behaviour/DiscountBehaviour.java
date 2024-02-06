package com.sofa.cinema.behaviour;

public class DiscountBehaviour implements PriceRuleBehaviour {
    private int totalTickets;
    private int dayNumber;
    private double ticketPrice;
    private boolean isStudentOrder;

    public DiscountBehaviour(int totalTickets, int dayNumber, double ticketPrice, boolean isStudentOrder) {
        this.totalTickets = totalTickets;
        this.dayNumber = dayNumber;
        this.ticketPrice = ticketPrice;
        this.isStudentOrder = isStudentOrder;
    }

    @Override
    public double getPriceRule() {
        return dayNumber >= 5 && dayNumber <= 7 && !this.isStudentOrder && totalTickets >= 6 ? ticketPrice * 0.9 : ticketPrice;
    }
}
