package com.sofa.cinema;

import com.sofa.cinema.adapter.INotification;
import com.sofa.cinema.adapter.MessageService;
import com.sofa.cinema.adapter.NotificationAdapter;
import com.sofa.cinema.notificationLibrary.Library;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@SpringBootApplication
public class CinemaApplication {
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
		Logger logger = Logger.getLogger("MAIN");

        // Adapter
        Library library = new Library();
        INotification notificationAdapter = new NotificationAdapter(library);
        MessageService messageService = new MessageService(notificationAdapter);

		// SCENARIO 1
		logger.info("SCENARIO 1: ");

		Order firstOrder = new Order(1, true, messageService, Medium.SMS);
		Movie firstMovie = new Movie("Batman");
		MovieScreening firstScreening = new MovieScreening(firstMovie, LocalDateTime.now().plusDays(4), 20.0);
		MovieTicket firstTicket = new MovieTicket(firstScreening, false, 5, 6);

		// Correct
		firstOrder.addTicket(firstTicket);
		firstOrder.removeTicket(firstTicket);

		// Wrong
		firstOrder.placeReservation();

		// Correct
		firstOrder.addTicket(firstTicket);

		// Wrong
		firstOrder.editReservation();

		// Correct
		firstOrder.placeReservation();
		firstOrder.ignorePayment();
		firstOrder.cancelOrder();

		// SCENARIO 2
		logger.info("SCENARIO 2: ");

		Order secondOrder = new Order(1, true,messageService, Medium.EMAIL);
		Movie secondMovie = new Movie("Batman");
		MovieScreening secondScreening = new MovieScreening(secondMovie, LocalDateTime.now().plusDays(4), 20.0);
		MovieTicket secondTicket = new MovieTicket(secondScreening, false, 5, 6);
		MovieTicket thirdTicket = new MovieTicket(secondScreening, false, 5, 7);

		// Correct
		secondOrder.addTicket(secondTicket);
		secondOrder.removeTicket(secondTicket);
		secondOrder.addTicket(thirdTicket);

		// Wrong
		secondOrder.editReservation();

		// Correct
		secondOrder.placeReservation();
		secondOrder.payOrder();
	}
}
