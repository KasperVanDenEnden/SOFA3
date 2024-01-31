package com.sofa.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import static com.sofa.cinema.TicketExportFormat.JSON;
import static com.sofa.cinema.TicketExportFormat.PLAIN_TEXT;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CinemaApplication.class, args);

		Order firstOrder = new Order(1,true);
		Movie movie = new Movie("Batman");
		MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.now(), 20.0);
		MovieTicket movieTicket = new MovieTicket(movieScreening, true, 5, 6);
		firstOrder.addSeatReservation(movieTicket);

		firstOrder.export(PLAIN_TEXT);
		firstOrder.export(JSON);

		double price = firstOrder.calculatePrice();
		System.out.println(price);
	}
}
