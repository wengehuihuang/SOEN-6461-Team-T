import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;

    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginFrame() {
        // Setting JFrame properties
        setTitle("iGo Login");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center JFrame in the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating and adding components
        JPanel mainPanel = new JPanel(new GridLayout(8, 5));
        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel()); // Empty label for alignment
        mainPanel.add(loginButton);

        // Adding ActionListener to login button
        loginButton.addActionListener(this);

        // Adding main panel to JFrame
        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        // Perform login authentication here
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // ...
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
