package com.sofa.cinema;

import java.util.List;

public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private List<MovieTicket> movieTickets;

    public Order(int orderNr, boolean isStudentOrder) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
        this.movieTickets = new ArrayList<MovieTicket>();
    }

    public int getOrderNr() {
        return this.orderNr;
    }

    public void addSeatReservation(MovieTicket ticket) {
        this.movieTickets.add(ticket);
    }

    public double calculatePrice() {
        double price = 0;

        for (MovieTicket ticket : this.movieTickets) {
            price = price + ticket.getPrice();
        }

        return price;
    }

    public void export(TicketExportFormat exportFormat) {
        switch (exportFormat) {
            case JSON:
                exportToJson();
                break;
            case PLAIN_TEXT:
                exportToPlainText();
                break;
            default:
            System.out.println("Unsupported export format");
        }
    }

    private void exportToPlainText() {
        String plainText = "Order Number: " + this.orderNr + "\n";
        plainText += "Is Student Order: " + this.isStudentOrder + "\n";
        plainText += "Movie Tickets:\n";
        for (MovieTicket ticket : this.movieTickets) {
            plainText += "  " + ticket.toString() + "\n";
        }

        System.out.println(plainText);
    }

    private void exportToJson() {


    }
}
