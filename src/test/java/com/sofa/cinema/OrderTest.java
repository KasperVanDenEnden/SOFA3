package com.sofa.cinema;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
 class OrderTest {
      String movieName= "Batman";

    @Test
     void calculatePrice_givenOneTicketWithSundayScreeningAndStudentButNoPremium_whenCalculatePrice_thenReturnStandardPrice() {
        Order order = new Order(1,true);
        Movie movie = new Movie(movieName);
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 4, 1, 1), 20.0);
        MovieTicket movieTicket = new MovieTicket(movieScreening, false, 5, 6);
        order.addSeatReservation(movieTicket);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(20);
    }

    @Test
     void calculatePrice_givenTwoTicketsWithSaturdayScreeningAndStudentButNoPremium_whenCalculatePrice_thenReturnStandardPriceOfFirstTicket() {
        Order order = new Order(1,true);
        Movie movie = new Movie(movieName);
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket movieTicketFirst = new MovieTicket(movieScreening, false, 5, 6);
        MovieTicket movieTicketSecond = new MovieTicket(movieScreening, false, 5, 7);
        order.addSeatReservation(movieTicketFirst);
        order.addSeatReservation(movieTicketSecond);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(10);
    }

    @Test
     void calculatePrice_givenOneTicketWithMondayScreeningAndStudentAndPremium_whenCalculatePrice_thenReturnPriceWithTwoExtraEuros() {
        Order order = new Order(1,true);
        Movie movie = new Movie(movieName);
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 1, 29, 1, 1), 15.0);
        MovieTicket movieTicket = new MovieTicket(movieScreening, true, 5, 6);
        order.addSeatReservation(movieTicket);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(17);
    }

    @Test
     void calculatePrice_givenOneTicketWithMondayScreeningAndNoStudentButWithPremium_whenCalculatePrice_thenReturnPriceWithThreeExtraEuros() {
        Order order = new Order(1,false);
        Movie movie = new Movie(movieName);
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 1, 29, 1, 1), 15.0);
        MovieTicket movieTicket = new MovieTicket(movieScreening, true, 5, 6);
        order.addSeatReservation(movieTicket);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(18);
    }

    @Test
     void calculatePrice_givenSixTicketsWithSaturdayScreeningAndNoStudentAndNoPremium_whenCalculatePrice_thenReturnPriceWithTenPercentOff() {
        Order order = new Order(1,false);
        Movie movie = new Movie(movieName);
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket movieTicketFirst = new MovieTicket(movieScreening, false, 5, 3);
        MovieTicket movieTicketSecond = new MovieTicket(movieScreening, false, 5, 4);
        MovieTicket movieTicketThird = new MovieTicket(movieScreening, false, 5, 5);
        MovieTicket movieTicketFourth = new MovieTicket(movieScreening, false, 5, 6);
        MovieTicket movieTicketFifth = new MovieTicket(movieScreening, false, 5, 7);
        MovieTicket movieTicketSixth = new MovieTicket(movieScreening, false, 5, 8);
        order.addSeatReservation(movieTicketFirst);
        order.addSeatReservation(movieTicketSecond);
        order.addSeatReservation(movieTicketThird);
        order.addSeatReservation(movieTicketFourth);
        order.addSeatReservation(movieTicketFifth);
        order.addSeatReservation(movieTicketSixth);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(54);
    }

    @Test
     void calculatePrice_givenTwoTicketsWithWednesdayScreeningAndNoStudentAndNoPremium_whenCalculatePrice_thenReturnStandardPriceOfFirstTicket() {
        Order order = new Order(1,false);
        Movie movie = new Movie("Batman");
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 1, 31, 1, 1), 10.0);
        MovieTicket movieTicketFirst = new MovieTicket(movieScreening, false, 5, 6);
        MovieTicket movieTicketSecond = new MovieTicket(movieScreening, false, 5, 7);
        order.addSeatReservation(movieTicketFirst);
        order.addSeatReservation(movieTicketSecond);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(10);
    }

    @Test
     void calculatePrice_givenNoTickets_whenCalculatePrice_thenReturnZero() {
        Order order = new Order(1,false);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(0);
    }


    @Test
     void calculatePrice_loopExecution() {
        MovieTicket mockTicket = Mockito.mock(MovieTicket.class);

        Order order = new Order(1,false);
        order.addSeatReservation(mockTicket);

        // set up the condition for the mock ticket
        Mockito.when(mockTicket.getDateAndTime()).thenReturn(LocalDateTime.now());

        // Call calculatePrice, which should iterate over the mock ticket
        order.calculatePrice();

        // Verify that the loop has run once (adjust the times parameter as needed)
        Mockito.verify(mockTicket, Mockito.times(1)).getDateAndTime();

    }

    @Test
     void calculatePrice_loopExecution_threeTimes() {
        MovieTicket mockTicket = Mockito.mock(MovieTicket.class);

        Order order = new Order(1,false);
        for(int i = 1; i <=3;i++) {
            order.addSeatReservation(mockTicket);
        }

        // set up the condition for the mock ticket
        Mockito.when(mockTicket.getDateAndTime()).thenReturn(LocalDateTime.now());

        // Call calculatePrice, which should iterate over the mock ticket
        order.calculatePrice();

        // Verify that the loop has run once (adjust the times parameter as needed)
        Mockito.verify(mockTicket, Mockito.times(3)).getDateAndTime();

    }
}
