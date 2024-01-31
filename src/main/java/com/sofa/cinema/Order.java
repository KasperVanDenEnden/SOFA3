package com.sofa.cinema;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private ArrayList<MovieTicket> movieTickets;

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

    public void export(TicketExportFormat exportFormat) throws Exception {
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

    private void exportToPlainText() throws Exception {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/main/java/com/sofa/cinema/exports/order.txt"))){
            String plainText = "Order Number: " + this.orderNr + "\n";
            plainText += "Is Student Order: " + this.isStudentOrder + "\n";
            plainText += "Movie Tickets:\n";
            for (MovieTicket ticket : this.movieTickets) {
                plainText += "  " + ticket.toString() + "\n";
            }
            br.write(plainText);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    private void exportToJson() throws Exception {
        try (FileWriter writer = new FileWriter("src/main/java/com/sofa/cinema/exports/order.json")){
            Gson gson = new Gson();
            gson.toJson(this.toString(), writer);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
