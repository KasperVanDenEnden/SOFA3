package     com.sofa.cinema.states;

import java.util.logging.Logger;
import com.sofa.cinema.MovieTicket;
import com.sofa.cinema.Order;

public class NoTicketState implements IOrderState {
    private Order order;
    final Logger logger = Logger.getLogger(this.getClass().getName());

    public NoTicketState(Order order) {
        this.order = order;
    }

    @Override
    public void addTicket(MovieTicket ticket) {
        this.order.addTicketToList(ticket);

        order.set_previousState(this); 
        order.set_currentState(new HasTicketsState(this.order));

        logger.info("The ticket had been added to the order!");
    }

    @Override
    public void removeTicket(MovieTicket ticket) {
        logger.info("Can't remove ticket, because order has no tickets!");
    }

    @Override
    public void editReservation() {
        logger.info("Can't edit reservation, because order has no tickets!");
    }

    @Override
    public void placeReservation() {
        logger.info("Can't place reservation, because order has no tickets!");
    }

     @Override
    public void payOrder() {
         logger.info("Can't pay order, because order has no tickets!");
    }

    @Override
    public void cancelOrder() {
        this.order.setCancelled(true);

        this.order.set_currentState(new CancelState());
        this.order.set_previousState(this);

        logger.info("This order has been cancelled!");
    }

    @Override
    public void ignorePayment() {
        logger.info("Can't ignore payment, because order has no tickets!");
    }
}
