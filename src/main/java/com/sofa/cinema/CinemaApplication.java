package com.sofa.cinema;

import com.sofa.cinema.Rules.PriceRuleBehaviour;
import com.sofa.cinema.exports.ExportJsonBehaviour;
import com.sofa.cinema.exports.ExportPlainTextBehaviour;
import com.sofa.cinema.tools.AssemblyScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.logging.Logger;

import java.time.LocalDateTime;

@SpringBootApplication
public class CinemaApplication {
	public static void main(String[] args) throws Exception {

		SpringApplication.run(CinemaApplication.class, args);

		final Logger logger = Logger.getLogger("MAIN");

		List<PriceRuleBehaviour> priceRuleBehaviours = AssemblyScanner.getInstancesOfType(PriceRuleBehaviour.class);



		// First order
		Order firstOrder = new Order(1,true, priceRuleBehaviours);
		// First movie
		Movie movie = new Movie("Batman");
		// First Batman screening
		MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.now().plusDays(4), 20.0);
		// Batman ticket
		MovieTicket movieTicket = new MovieTicket(movieScreening, false, 5, 6);
		// Add reservations
		firstOrder.addSeatReservation(movieTicket);
		// Export
		firstOrder.export(new ExportPlainTextBehaviour());
		firstOrder.export(new ExportJsonBehaviour());

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
 		Order orderTarzan1 = new Order(2,true, priceRuleBehaviours);
 		orderTarzan1.addSeatReservation(tarzanTicketOne);
 		orderTarzan1.addSeatReservation(tarzanTicketTwo);
        // Export
 		orderTarzan1.export(new ExportPlainTextBehaviour());
 		orderTarzan1.export(new ExportJsonBehaviour());
	}
}
