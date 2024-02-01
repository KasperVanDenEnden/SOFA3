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
		MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.now().plusDays(4), 20.0);
		MovieTicket movieTicket = new MovieTicket(movieScreening, false, 5, 6);
		firstOrder.addSeatReservation(movieTicket);

		firstOrder.export(PLAIN_TEXT);
		firstOrder.export(JSON);

		double price = firstOrder.calculatePrice();
		System.out.println(price);
		// ----------------------------------------------------
		// Movies
		Movie tarzan = new Movie("Tarzan");

		// Screenings
		MovieScreening tScreeningOne = new MovieScreening(tarzan, LocalDateTime.now().plusDays(4), 5.5);

		// Tickets
		// Tarzan 1
		MovieTicket tarzanTicketOne = new MovieTicket(tScreeningOne, true, 7,8 );
		MovieTicket tarzanTicketTwo = new MovieTicket(tScreeningOne, true,7,9);


		// Order
		Order orderTarzan1 = new Order(1,true);
		orderTarzan1.addSeatReservation(tarzanTicketOne);
		orderTarzan1.addSeatReservation(tarzanTicketTwo);

		orderTarzan1.export(PLAIN_TEXT);
		orderTarzan1.export(JSON);

	}
}
