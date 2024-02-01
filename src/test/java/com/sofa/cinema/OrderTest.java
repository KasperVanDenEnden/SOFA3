package com.sofa.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderTest {
    @Test
    public void givenOneTicketWithWeekendScreeningAndStudent_whenCalculatePrice_thenReturnStandardPrice() {
        Order order = new Order(1,true);
        Movie movie = new Movie("Batman");
        MovieScreening movieScreening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 4, 1, 1), 20.0);
        MovieTicket movieTicket = new MovieTicket(movieScreening, false, 5, 6);
        order.addSeatReservation(movieTicket);

        double totalPrice = order.calculatePrice();

        assertThat(totalPrice).isEqualTo(20);
    }
}
