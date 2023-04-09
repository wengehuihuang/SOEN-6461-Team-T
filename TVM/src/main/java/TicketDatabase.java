import java.util.HashMap;

public class TicketDatabase {
    private static HashMap<String, Ticket> database;

    static {
        // Creating ticket database
        database = new HashMap<String, Ticket>();
        database.put("1", new Ticket("1-trip fare, All Modes A3", 3.50));
        database.put("2", new Ticket("2-trip fare, All Modes A3", 6.50));
        database.put("3", new Ticket("10-trip fare, All Modes A3", 31.50));
        database.put("4", new Ticket("Unlimited evening pass", 5.75));
        database.put("5", new Ticket("24-hour pass, All Modes A1 3", 11.00));
        database.put("6", new Ticket("Unlimited weekend pass1", 14.75));
        database.put("7", new Ticket("3-day pass, All Modes A1 3", 21.25));
        database.put("8", new Ticket("Weekly pass, All Modes A1 3", 29.00));
        database.put("9", new Ticket("Monthly pass, All Modes A1 3", 94.00));
        database.put("10", new Ticket("YUL Montréal-Trudeau Airport (747)1 2 3", 11.00));
    }

    public static Ticket getTicketPrice(String ticketNumber) {
        // Retrieve ticket information from database
        Ticket ticket = database.get(ticketNumber);

        // Check payment method
        if (ticket != null ) {
            return ticket;

        }

        return null;
    }
}