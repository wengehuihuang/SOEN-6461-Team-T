import sun.misc.VM;

import javax.swing.*; // This is all of the JButtons and JLabels, etc.
import java.awt.*; // This is the Toolkit and the Dimension Stuff.
import java.awt.event.*; // This is for the actionListeners and actionPerformed function.
import javax.swing.JOptionPane; // This is for graphical IO stuff.

public class TVMGUI extends JFrame implements ActionListener {
    // This is the class that we are using, notice the differences between this
    // and the stardard class format.
    // We use extends JFrame because it IS a JFrame. This is a user interface.
    // The implements ActionListener is for picking up actions on buttons.
    // We need this or otherwise buttons clicked wouldn't do anything.

    // These variables are changable.
    private int StartingBalance = 0; // This is how much money is in the machine to start.
    public boolean CloseAfter = false; // This tells us whether we are to exit the program
    // after buying an item.
    // I made CloseAfter public so that it can be set in the VendMachine class file.

    private int Width = 350; // This is the width of the interface.
    private int Height = 225; // This is the height of the interface.

    // These are various things, seem to be out of order for classification, but I will
    // explain them.
    private JButton Buttons[]; // This is an array of all of the buttons on this IFace.
    private int i; // This is the integer I use for all of my for loops.
    public TVM Machine = new TVM(StartingBalance, CloseAfter); // This creates a new machine in VM.java
    // to be used in this class.
    public JLabel About = new JLabel("   Vending Machine");
    // This makes a little about label that just says "Vending Machine".
    public JLabel Bal = new JLabel("   Balance: " + Machine.getPrettyBalance());
    // This is the label that holds the balance and makes it pretty.
    // We needed this at the end because the Machine has to be created before we
    // can get the "pretty balance" from it.

    public TVMGUI() { // This is what we use in VendMachine to create the GUI.

		/* The following here is mostly GUI Stuff, and it is almost all taken from
			our text book.
			I do not take credit for writing this code but because I felt that this
			was NOT the object of our assignment, I felt that there was nothing wrong
			with copying this code to use in the creation of the user interface.
		*/

        setTitle("Vending Machine"); // This sets the title of the window.
        // This uses a toolkit and Dimension object to get the screen size, and
        // from that, center our window on the screen.
        // Written by Andrea Steelman.
        Toolkit myTK = Toolkit.getDefaultToolkit();
        Dimension myD = myTK.getScreenSize();
        setBounds((myD.width - Width)/2, (myD.height - Height)/2, Width, Height);

        // This code was also taken directly from the book.
        // All this does is listen for the window being closed (Alt + F4, click the x, you name it)
        // this picks it up and ends the program.
        addWindowListener(new WindowAdapter(){
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              } // END windowClosing(WindowEvent e)
                          }// END WindowAdapter()
        ); // END addWindowListener()

        // Now this is our GUI stuff.
        Buttons = new JButton[12]; // This actually creates the GUI Button array with 12 buttons.
        Machine.Initialize(); // This initializes the machine so that we dont have to manually input the
        // descriptions and prices.

        for (i = 0 ; i < 6 ; i++) { // This for loop creates the seperate buttons with the correct captions
            // but scrolling through the indeces and setting the captions.
            Buttons[i] = new JButton(Machine.getDescription(i) + " - " + Machine.PrettyMoney(Machine.getPrice(i)));
            // This actually carries out the action
        } // END for (i = 0 ; i < 6 ; i++)

        // The following creates the buttons that deal with inputting and recieving
        // money. This also labels the buttons appropriately.
        Buttons[6] = new JButton("Dollar");
        Buttons[7] = new JButton("Half Dollar");
        Buttons[8] = new JButton("Quarter");
        Buttons[9] = new JButton("Dime");
        Buttons[10] = new JButton("Nickel");
        Buttons[11] = new JButton("Coin Return");

        // This creates the content pane to which we will add all of our buttons and labels and whatnot.
        Container pane = getContentPane(); // Get the pane.
        pane.setLayout(new GridLayout(7,2)); // This sets the layout to the grid style.
        // This has 7 down and 2 across.
        for(i = 0 ; i < 6; i++) { // This Goes through and adds the buttons to the content pane,
            // it also adds action listeners to all of the buttons so that we can know when they
            // are clicked.
            pane.add(Buttons[i]); // Add buttons to content pane (pane).
            pane.add(Buttons[i+6]); // This makes sure that the buttons alternate
			/* The above two lines of code might seem pointless, but they simply to make the
				user interface look like it does in the command line version of the program.
				The buttons go across and then down, which may be a little bit tricky,
				but by alternating between drink buttons and money buttons this way, this
				makes sure that all of the drink buttons are on the left, and the money
				buttons are on the right.
				This goes through 0 to 5, and does the following:
				adds button 0 adds button 6
				adds button 1 adds button 7
				...				...
				adds button 5 adds button 11

				This lines the buttons up just right using this alternating method.
			*/
            Buttons[i].addActionListener(this); // add action listeners to the buttons.
            Buttons[i+6].addActionListener(this); // This makes sure that all of the buttons
            // have listeners so we know when actions are performed.
        } // END for(i = 0 ; i < 12; i++)

        pane.add(Bal); // This adds the Balance label to the content pane.
        pane.add(About); // This adds the About label to the content pane.

    } // END VMGUI()

    /* actionPerformed function catches whenever an object
        with an action listener attached is clicked or
        "has an action performed" upon it.
        This is where the code determines <what to do>
        when something happens. Probably the only useful
        code. The rest just does the interface.
    */
    public void actionPerformed(ActionEvent e) { // BEGIN actionPerformed(ActionEvent e)
        boolean Success;
        Machine.Initialize();
        // This initializes the Vending Machine which tells the machine to create the
        // names and prices of the drinks in our machine so that we dont have to manually
        // do it!
        Object theButton = e.getSource();
        // This creates an object called theButton which is the source of the event.
        // When we are checking what happened, we need to know where the event came
        // from so we know what button was clicked.

		/* The following code is very short as opposed to what I may have had to write
			so I will walk you through it.
			First, this goes through the buttons [6] - [11] which are the buttons having
			to do with money. (All are input money, or coin return).
			It starts counting in the for loop from 6 - 11, and in there, it checks if
			the source is Buttons[6] all the way through Buttons[12].
			If the number is in there, then it goes to a switch statement that looks at
			ONLY i. Then it checks if it is actually 6 - 12 and depending on i, it figures
			out what to do.
			This is not the most resource saving code, but for this assignment I feel
			that shorter is a trade off with speed/resource use.
		*/
        for(i = 6; i<12 ; i++) { // This for loop starts us off by "counting" through 12 from 6.
            if(theButton == Buttons[i]) { // If we have a match on source with Button number then:
                switch(i) { // Check out only i to see what number it is and at accordingly.
                    case 6: // If we have six, that is the button called Dollar, which means
                        // that we add a dollar.
                        Machine.AddMoney(100); // So add a dollar to our public Machine.
                        break; // This is so that we dont execute through the case 7: etc
                    case 7: // If we have Buttons[7] that means we pressed, HALF DOLLAR
                        Machine.AddMoney(50); // So we add 50 cents to the public Machine.
                        break; // So we dont keep cycling through.
                    case 8: // This means that we hit the quarter button.
                        Machine.AddMoney(25); // Add 25 cents.
                        break; // Stop
                    case 9: // This means we hit the dime button.
                        Machine.AddMoney(10); // Add 10 cents.
                        break; // Stop
                    case 10: // This means we hit the nickel button.
                        Machine.AddMoney(5); // Add 5 cents.
                        break; // Stop.
                    case 11: // We want our money back! They dont have the soda we want!
                        Machine.ReturnChange(); // Return our change. This is explained in VM.java
                        System.exit(0); // Exit our program. Necessary because of the GUI.
                        // break; // Stop (actually not necessary).
                } // END switch(i)
            } // END if(theButton == Buttons[i])
        }// END for(i = 6; i<12 ; i++)
        for(i = 0; i < 6; i++) { // This for loop checks if we are buying a soda.
            // It uses the same method as above except it is a little easier.
            // Instead of a switch, we just cycle through, and it we have a match
            // the we buy the item i.
            if(theButton == Buttons[i]) { // If the source is the same as the index i.
                Success = Machine.BuyItem(i); // Buy that item number. Starts with zero like the items
                // in VM.java
                // This checks the Close After variable which asks us whether we want to exit after
                // the user purchases an item.
                if ((Machine.getCA() == true) || (Success == true)) {
                    // This has just been updated. What it does now is it checks if we want to
                    // close after any of the drink dispensing buttons have been clicked, or
                    // more explicitly if the CloseAfter value is true, OR it will check if
                    // we successfully purchased a drink from the machine, at which point,
                    // whether close after is true or false, we are done and should return
                    // the change and exit the program (the machine is done).
                    Machine.ReturnChange(); // I removed this line from the BuyItem function
                    // because the user in this case might not want their change back just yet.
                    // as a matter of fact, when you dont have enough money, a vending machine
                    // waits until you put in enough or pick something else.
                    // Either way, this returns the change if we are in the "close after" mode.
                    // If not, we just go right back and keep our balance.
                    System.exit(0); // The BuyItem function Returns the change, so we dont need to
                    // put that, but it doesn't exit the program, so we need to do that.
                    // Bye Bye.
                } // END if (Machine.getCA() == true)
            } // END if(theButton == Buttons[i])
        } // END for(i = 0; i < 6; i++)
        Bal.setText("   Balance: " + TVM.getPrettyBalance());
        // As long as we haven't exited (which means we didnt buy something or hit the
        // coin return button, we will be getting to this line. So that means this will
        // display the new balance in a pretty form in the label at the bottom called
        // Bal so that after we add money we will see what happened to our balance.
    } // END actionPerformed(ActionEvent e)
} // END VMGUI