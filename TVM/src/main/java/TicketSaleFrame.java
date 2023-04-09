

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicketSaleFrame extends JFrame implements ActionListener {
    private JComboBox<String> ticketTypeCombo;
    private JTextField quantityField;
    private JButton purchaseButton;

    public TicketSaleFrame() {
        // Setting JFrame properties
        setTitle("Ticket Sale");
        setSize(400, 200);
        setLocationRelativeTo(null); // Center JFrame in the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating and adding components
        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        JLabel ticketTypeLabel = new JLabel("Ticket Type: ");
        ticketTypeCombo = new JComboBox<>(new String[]{"1-trip fare, All Modes A3", "2-trip fare, All Modes A3",
                "10-trip fare, All Modes A3", "Unlimited evening pass", "24-hour pass, All Modes A1 3",
                "Unlimited weekend pass", "3-day pass, All Modes A1 3", "Weekly pass, All Modes A1 3",
                "Monthly pass, All Modes A1 3", "YUL Montréal-Trudeau Airport (747)1 2 3"});
        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityField = new JTextField();
        purchaseButton = new JButton("Purchase");

        mainPanel.add(ticketTypeLabel);
        mainPanel.add(ticketTypeCombo);
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel()); // Empty label for alignment
        mainPanel.add(purchaseButton);

        // Adding ActionListener to purchase button
        purchaseButton.addActionListener(this);

        // Adding main panel to JFrame
        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        // Retrieve ticket type and quantity information
        String ticketType = (String) ticketTypeCombo.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());

        // Calculate total cost
        double cost = Ticket.calculateCost(ticketType, quantity);

        // Process payment
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        boolean paymentSuccess = paymentProcessor.processPayment(cost);

        // Show payment result to user
        if (paymentSuccess) {
            JOptionPane.showMessageDialog(this, "Payment successful! Your ticket(s) will be printed shortly.");
        } else {
            JOptionPane.showMessageDialog(this, "Payment error. Please try again.");
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicketSaleFrame tick = new TicketSaleFrame();
                tick.setVisible(true);
            }
        });
    }
}