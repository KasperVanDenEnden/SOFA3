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
		Movie frozen = new Movie("Frozen");

		// Screenings
		// Tarzan
		MovieScreening tScreeningOne = new MovieScreening(tarzan, LocalDateTime.now().plusDays(4), 5.5);
		MovieScreening tScreeningTwo = new MovieScreening( tarzan, LocalDateTime.now().plusDays(4) , 5.5);

		// Frozen
		MovieScreening fScreeningOne = new MovieScreening(tarzan, LocalDateTime.now().plusDays(4), 10.0);
		MovieScreening fScreeningTwo = new MovieScreening( tarzan,LocalDateTime.now().plusDays(4) , 10.0);


		// Tickets
		// Tarzan 1
		MovieTicket TarzanTicketOne = new MovieTicket(tScreeningOne, true, 7,8 );
		MovieTicket TarzanTicketTwo = new MovieTicket(tScreeningOne, true,7,9);

		// Tarzan 2

		// Frozen 1

		// Frozen 2

		// Order
		Order OrderTarzan1 = new Order(1,true);
		OrderTarzan1.addSeatReservation(TarzanTicketOne);
		OrderTarzan1.addSeatReservation(TarzanTicketTwo);

		OrderTarzan1.export(PLAIN_TEXT);
		OrderTarzan1.export(JSON);

	}
}
