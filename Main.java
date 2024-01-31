public class Main {
    public static void main(String[] args) {

    TicketExportFormat plain = TicketExportFormat.PLAIN_TEXT;
    TicketExportFormat json = TicketExportFormat.JSON;

    Order order1 = new Order(1,true);
    order1.export(plain);

        System.out.println("Hello, Java Project!");
    }
}
