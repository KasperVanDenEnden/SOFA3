import java.util.ArrayList;

public class Order {
    private int orderNr;
    private boolean isStudentOrder;
    private ArrayList<MovieTicket> movieTickets;

    public Order(int orderNr, boolean isStudentOrder) {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;
    }

    public int getOrderNr() {
        return this.orderNr;
    }

    public void addSeatReservation(MovieTicket ticket) {
        this.movieTickets.add(ticket);
    }

    public double calculatePrice() {
        double price = 0;

        for (MovieTicket ticket : this.movieTickets) {
            price = price + ticket.getPrice();
        }

        return price;
    }

    public void export(TicketExportFormat exportFormat) {
        
    }
}
