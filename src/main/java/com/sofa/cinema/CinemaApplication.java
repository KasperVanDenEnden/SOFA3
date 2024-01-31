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

		// Movies
		Movie tarzan = new Movie("Tarzan");
		Movie frozen = new Movie("Frozen");

		// Screenings
			// Tarzan
			LocalDateTime tOneDateTime = LocalDateTime.of(2024, 2, 1, 18, 30); // 2024-02-01T18:30
			LocalDateTime tTwoDateTime = LocalDateTime.of(2024, 2, 1, 21, 0);  // 2024-02-01T21:00

			MovieScreening tScreeningOne = new MovieScreening(tarzan, tOneDateTime, 5.5);
			MovieScreening tScreeningTwo = new MovieScreening( tarzan, tTwoDateTime , 5.5);

			// Frozen
			LocalDateTime fOneDateTime = LocalDateTime.of(2024, 2, 1, 18, 30); // 2024-02-01T18:30
			LocalDateTime fTwoDateTime = LocalDateTime.of(2024, 2, 1, 21, 0);  // 2024-02-01T21:00

			MovieScreening fScreeningOne = new MovieScreening(tarzan, tOneDateTime, 10.0);
			MovieScreening fScreeningTwo = new MovieScreening( tarzan, tTwoDateTime , 10.0);


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
