import java.util.Random;

public class PaymentProcessor {
    // Simulate a 80% success rate for payments
    private static final double SUCCESS_RATE = 0.8;

    public static boolean processPayment(double amount) {
        // Simulate payment processing
        Random random = new Random();
        boolean success = random.nextDouble() < SUCCESS_RATE;

        // Print payment result
        if (success) {
            System.out.println("Payment of $" + amount + " was successful.");
        } else {
            System.out.println("Payment of $" + amount + " failed. Please try again.");
        }

        return success;
    }
}