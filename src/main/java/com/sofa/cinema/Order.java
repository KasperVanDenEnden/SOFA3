package com.sofa.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sofa.cinema.errors.ExportException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private ArrayList<MovieTicket> movieTickets;
    // Create a logger for the Order class
    private static final Logger logger = Logger.getLogger("ORDER");
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
        double totalPrice = 0;

        for (int i = 0; i < movieTickets.size(); i++) {
            MovieTicket ticket = movieTickets.get(i);
            int dayNumber = ticket.getDateAndTime().getDayOfWeek().getValue();

            if (((i + 1.0) % 2 == 0) && (this.isStudentOrder || (dayNumber >= 1 && dayNumber <= 4))) {
                // Free when second ticket and student or when second ticket and mo/tue/we/thu
                continue;
            }

            double ticketPrice = ticket.getPrice();
            boolean isPremium = ticket.isPremiumTicket();

            if (isPremium) {
                if (isStudentOrder) {
                    // 2 euros extra when premium ticket and student
                    ticketPrice += 2.0;
                } else {
                    // 3 euros extra when premium ticket and no student
                    ticketPrice += 3.0;
                }
            }

            if (dayNumber >= 5 && dayNumber <= 7 && !this.isStudentOrder && movieTickets.size() >= 6) {
                // When weekend and no student and more or equal then 6 tickets 10% discount
                totalPrice += ticketPrice * 0.9;
            } else {
                // Else normal price
                totalPrice += ticketPrice;
            }
        }

        return totalPrice;
    }

    public void export(TicketExportFormat exportFormat) throws ExportException {
        switch (exportFormat) {
            case JSON:
                exportToJson();
                break;
            case PLAIN_TEXT:
                exportToPlainText();
                break;
            default:
            logger.info("Unsupported export format");
        }
    }

    private void exportToPlainText() throws ExportException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/main/java/com/sofa/cinema/exports/order.txt"))){
            StringBuilder plainText = new StringBuilder("Order Number: " + this.orderNr + "\n");
            plainText.append("Is Student Order: ").append(this.isStudentOrder).append("\n");
            plainText.append("Movie Tickets:\n");
            for (MovieTicket ticket : this.movieTickets) {
                plainText.append("  ").append(ticket.toString()).append("\n");
            }
            br.write(plainText.toString());
        } catch (IOException e) {
            throw new ExportException("Error exporting to plain text", e);
        }
    }

    private void exportToJson() throws ExportException {
        try (FileWriter writer = new FileWriter("src/main/java/com/sofa/cinema/exports/order.json")){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty-printing
            objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
            objectMapper.writeValue(writer, this);
        } catch (IOException e) {
            throw new ExportException("Error exporting to JSON", e);
        }
    }
}
