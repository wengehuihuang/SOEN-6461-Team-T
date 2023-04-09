public class Ticket {
    private String type;
    private double price;

    public Ticket(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public static double calculateCost(String ticketType, int quantity) {
        double cost = 0.0;
        switch (ticketType) {
            case "1-trip fare, All Modes A3":
                cost = 3.50 * quantity;
                break;
            case "2-trip fare, All Modes A3":
                cost = 6.50 * quantity;
                break;
            case "10-trip fare, All Modes A3":
                cost = 31.50 * quantity;
                break;
            case "Unlimited evening pass":
                cost = 5.75 * quantity;
                break;
            case "24-hour pass, All Modes A1 3":
                cost = 11.00 * quantity;
                break;
            case "Unlimited weekend pass":
                cost = 14.75 * quantity;
                break;
            case "3-day pass, All Modes A1 3":
                cost = 21.25 * quantity;
                break;
            case "Weekly pass, All Modes A1 3":
                cost = 29.00 * quantity;
                break;
            case "Monthly pass, All Modes A1 3":
                cost = 94.00 * quantity;
                break;
            case "YUL Montréal-Trudeau Airport (747)1 2 3":
                cost = 11.00 * quantity;
                break;
            default:
                System.out.println("Invalid ticket type!");
        }
        return cost;
    }
}