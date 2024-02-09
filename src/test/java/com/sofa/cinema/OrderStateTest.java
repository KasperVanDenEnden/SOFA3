package com.sofa.cinema;

import com.sofa.cinema.states.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderStateTest {
    String movieName = "Batman";

    @Test
    void givenNoTickets_whenAddTicket_thenSwitchToHasTicketsState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);
    }

    @Test
    void givenNoTicket_WhenCanceled_thenSwitchToCancelState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);

        order.cancelOrder();

        assertThat(order.get_currentState()).isInstanceOf(CancelState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);
    }

    @Test
    void givenOneTicket_whenCancel_thenSwitchToCancelState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);
        order.cancelOrder();

        assertThat(order.get_currentState()).isInstanceOf(CancelState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);
    }

    @Test
    void givenOneTicket_whenRemoveTicket_thenSwitchToNoTicketState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);
        order.removeTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(NoTicketState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);
    }

    @Test
    void givenTwoTickets_whenRemoveTicket_thenNoSwitchFromHasTicketState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);
        order.addTicket(ticket);
        order.removeTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);
    }

    @Test
    void givenNoTickets_whenAddTicketAndReserve_thenSwitchToHasTicketsStateAndPlacedReservationState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);
    }

    @Test
    void givenOneTicket_whenReserveAndEdit_thenSwitchToHasTicketStateFromPlacedReservationState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.editReservation();

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);
    }

    @Test
    void givenNoTickets_whenAddAndReserveAndIgnorePayment_thenSwitchToHasTicketStateAndPlacedReservationStateAndFullDayLeftState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(FullDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);
    }

    @Test
    void givenOneTicket_when24HoursAreLeftStartEdit_thenSwitchToHasTicketStateFromFullDayLeftState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(FullDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);

        order.editReservation();

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(FullDayLeftState.class);
    }

    @Test
    void givenNoTickets_whenAddAndReserveAndIgnoreAndPay_thenSwitchToHasTicketStateAndPlacedReservationStateAndFullDayLeftStateAndFullyProcessedState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(FullDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);

        order.payOrder();

        assertThat(order.get_currentState()).isInstanceOf(FullyProcessedState.class);
        assertThat(order.get_previousState()).isInstanceOf(FullDayLeftState.class);
    }

    @Test
    void givenOneTicket_whenReservedAnd12HoursAreLeft_thenSwitchFromFullDayLeftStateToHalfDayLeftState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(FullDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(HalfDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(FullDayLeftState.class);
    }

    @Test
    void givenOneTicket_whenReservedAnd12HoursAreLeft_thenSwitchFromFullDayLeftStateToHalfDayLeftStateToCancelledState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(FullDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(HalfDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(FullDayLeftState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(CancelState.class);
        assertThat(order.get_previousState()).isInstanceOf(HalfDayLeftState.class);
    }

    @Test
    void givenNoTickets_whenAddAndReserveAndIgnoreAndCancel_thenSwitchToHasTicketStateAndPlacedReservationStateAndFullDayLeftStateAndCancelledState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.ignorePayment();

        assertThat(order.get_currentState()).isInstanceOf(FullDayLeftState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);

        order.cancelOrder();

        assertThat(order.get_currentState()).isInstanceOf(CancelState.class);
        assertThat(order.get_previousState()).isInstanceOf(FullDayLeftState.class);
    }

    @Test
    void givenOneTicket_whenCancelingTheReservation_thenSwitchFromPlacedReservationStateToCanceledState() {
        Order order = new Order(1, true);
        Movie movie = new Movie(movieName);
        MovieScreening screening = new MovieScreening(movie, LocalDateTime.of(2024, 2, 3, 1, 1), 10.0);
        MovieTicket ticket = new MovieTicket(screening, false, 5, 6);

        order.addTicket(ticket);

        assertThat(order.get_currentState()).isInstanceOf(HasTicketsState.class);
        assertThat(order.get_previousState()).isInstanceOf(NoTicketState.class);

        order.placeReservation();

        assertThat(order.get_currentState()).isInstanceOf(PlacedReservationState.class);
        assertThat(order.get_previousState()).isInstanceOf(HasTicketsState.class);

        order.cancelOrder();

        assertThat(order.get_currentState()).isInstanceOf(CancelState.class);
        assertThat(order.get_previousState()).isInstanceOf(PlacedReservationState.class);

    }
}
