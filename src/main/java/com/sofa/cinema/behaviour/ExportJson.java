package com.sofa.cinema.behaviour;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sofa.cinema.Order;
import com.sofa.cinema.errors.ExportException;

import java.io.FileWriter;
import java.io.IOException;

public class ExportJson implements ExportBehaviour {
    public void export(Order order) throws ExportException {
        try (FileWriter writer = new FileWriter("src/main/java/com/sofa/cinema/exports/order-" + order.getOrderNr() + ".json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty-printing
            objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
            objectMapper.writeValue(writer, order);
        } catch (IOException e) {
            throw new ExportException("Error exporting to JSON", e);
        }
    }
}
