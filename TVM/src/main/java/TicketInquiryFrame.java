import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicketInquiryFrame extends JFrame implements ActionListener {
    private JTextField ticketNumberField;
    private JButton inquiryButton;
    private JTextArea resultArea;

    public TicketInquiryFrame() {
        // Setting JFrame properties
        setTitle("Ticket Inquiry");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center JFrame in the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating and adding components
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JLabel ticketNumberLabel = new JLabel("Ticket Number: ");
        ticketNumberField = new JTextField();
        inquiryButton = new JButton("Inquire");
        resultArea = new JTextArea();

        mainPanel.add(ticketNumberLabel);
        mainPanel.add(ticketNumberField);
        mainPanel.add(inquiryButton);

        // Adding ActionListener to inquiry button
        inquiryButton.addActionListener(this);

        // Adding result area to a scroll pane and adding it to main panel
        JScrollPane scrollPane = new JScrollPane(resultArea);
        mainPanel.add(scrollPane);

        // Adding main panel to JFrame
        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        // Retrieve ticket number information
        String ticketNumber = ticketNumberField.getText();

        // Retrieve ticket information from database
        Ticket ticket = TicketDatabase.getTicketPrice(ticketNumber);

        // Display ticket information to user
        if (ticket != null) {
            resultArea.setText("Ticket Number: " + ticketNumber + "\n" +
                    "Type: " + ticket.getType() + "\n" +
                    "Price: $" + ticket.getPrice());
        } else {
            resultArea.setText("Ticket number not found.");
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
