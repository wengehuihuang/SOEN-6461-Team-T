import javax.swing.*; // Import our javax swing components.

public class TicketVendMachine { // This is the class called VendMachine (for VendMachine.java)

    public static void main(String args[]) {
        // This is the first thing launched when we run the program.
        BuildMachine(); // This uses the "Build Machine" function
        //which goes down below and activates the JFrame so it is shown on the screen.
        // This Uses the VMGUI.class to do this.
    } // END main(String args[])

    public static void BuildMachine() {
        // This "Builds" the machine. It creates a new object called
        // VendingMachine which is a JFrame object, and shows it on the
        // screen.
        JFrame TicketVendingMachine = new TVMGUI();
        // Create new JFrame object, which utilizes the VMGUI.class and the VMGUI function
        TicketVendingMachine.show(); // Show the vending machine jframe. Make it visible to the
        // user.
    } // END BuildMachine()

} // END VendMachine 