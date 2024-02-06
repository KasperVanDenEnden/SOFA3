package com.sofa.cinema.behaviour;

import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.errors.ExportException;
import com.sofa.cinema.Order;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExportPlainTextBehaviour implements ExportBehaviour {
    public void export(Order order) throws ExportException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/main/java/com/sofa/cinema/exports/order-" + order.getOrderNr() + ".txt"))) {
            StringBuilder plainText = new StringBuilder("Order Number: " + order.getOrderNr() + "\n");
            plainText.append("Is Student Order: ").append(order.isStudentOrder()).append("\n");
            plainText.append("Movie Tickets:\n");
            for (MovieTicket ticket : order.getMovieTickets()) {
                plainText.append("  ").append(ticket.toString()).append("\n");
            }
            br.write(plainText.toString());
        } catch (IOException e) {
            throw new ExportException("Error exporting to plain text", e);
        }
    }
}
