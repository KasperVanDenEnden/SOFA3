package com.sofa.cinema;

import com.sofa.cinema.behaviour.ExportJson;
import com.sofa.cinema.behaviour.ExportPlainText;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

import java.time.LocalDateTime;

import static com.sofa.cinema.TicketExportFormat.JSON;
import static com.sofa.cinema.TicketExportFormat.PLAIN_TEXT;

@SpringBootApplication
public class CinemaApplication {


	public static void main(String[] args) throws Exception {

		SpringApplication.run(CinemaApplication.class, args);

		final Logger logger = Logger.getLogger("MAIN");

		// First order
		Order firstOrder = new Order(1,true);
		// First movie
		Movie movie = new Movie("Batman");
		// First Batman screening
		MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.now().plusDays(4), 20.0);
		// Batman ticket
		MovieTicket movieTicket = new MovieTicket(movieScreening, false, 5, 6);
		// Add reservations
		firstOrder.addSeatReservation(movieTicket);
		// Export
		firstOrder.export(new ExportPlainText());
		firstOrder.export(new ExportJson());

		// Calculate total price
		double price = firstOrder.calculatePrice();
		logger.info(String.format("Price: %s", price));

		// Second movie
 		Movie tarzan = new Movie("Tarzan");
 		// Tarzan screening
 		MovieScreening tScreeningOne = new MovieScreening(tarzan, LocalDateTime.now().plusDays(4), 5.5);
 		// Tarzan screening tickets
 		MovieTicket tarzanTicketOne = new MovieTicket(tScreeningOne, true, 7,8 );
 		MovieTicket tarzanTicketTwo = new MovieTicket(tScreeningOne, true,7,9);
 		// Second order
 		Order orderTarzan1 = new Order(2,true);
 		orderTarzan1.addSeatReservation(tarzanTicketOne);
 		orderTarzan1.addSeatReservation(tarzanTicketTwo);
        // Export
 		orderTarzan1.export(new ExportPlainText());
 		orderTarzan1.export(new ExportJson());
	}
}
