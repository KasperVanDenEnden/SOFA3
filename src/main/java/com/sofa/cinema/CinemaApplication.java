package com.sofa.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.sofa.cinema.TicketExportFormat.JSON;
import static com.sofa.cinema.TicketExportFormat.PLAIN_TEXT;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CinemaApplication.class, args);

		Order firstOrder = new Order(1,true);
		firstOrder.export(PLAIN_TEXT);
		firstOrder.export(JSON);
	}
}
