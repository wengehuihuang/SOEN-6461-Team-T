import javax.swing.JOptionPane;

public class TVM { // This is our main class which is called in the main
    // function of VendMachine.java.

	/* The following is the variables used to store drink
		prices and descriptions:
	*/

    final static int PRICE_0 = 105;
    final static int PRICE_1 = 145;
    final static int PRICE_2 = 215;
    final static int PRICE_3 = 295;
    final static int PRICE_4 = 995;
    final static int PRICE_5 = 405;

    final static String DESC_0 = "Pepsi";
    final static String DESC_1 = "Mountain Dew";
    final static String DESC_2 = "Coke";
    final static String DESC_3 = "Diet Coke";
    final static String DESC_4 = "Blood";
    final static String DESC_5 = "Crab Juice";

    private static int balance; // This is the integer that is used
    // to hold the balance of the user.
    private static boolean CA = false;
    // This tells us that when a user buys a drink the machine doesnt exit,
    // we wait until the coin return button is pressed no matter what.
    // This makes sense for a user interface that we are using this with.

    private static int Price[] = new int[6];
    // This array holds the prices of the 6 items in the machine.
    private static String Description[] = new String[6];
    // This array holds the descriptions of the 6 items in the machine.
    private static int MaxLen = 0; // This variable (int) is used for formatting
    // the menu so that it looks nice.
    // I still am unsure how to make things pretty in the JOptionPane
    // but this simply makes each string description the same length
    // so that they appear pretty on the screen.

    public static void setPrice(int ItemNumber, int thePrice) {
        // This function does exactly what it says, it takes the item number
        // and the price you want to assign, and it sets that item to that price.
		/* Example:
			setPrice(0, 165);
			Sets the price of item 0 to $1.65
		*/
        Price[ItemNumber] = thePrice; // set the price of our item.
    } // END setPrice(int ItemNumber, int thePrice)

    public static int getPrice(int ItemNumber) {
        // This function does again exactly what it says, you ask it for the price
        // and it will return a price in an integer form.
		/* Example:
			int x = 0;
			x = getPrice(0);
			This will tell us that item 0 costs $1.65
		*/
        return Price[ItemNumber]; // Return the price of our item.
    } // END getPrice(int ItemNumber)

    public static void setDescription(int ItemNumber, String theDescription) {
        // This function is pretty self explanatory as well, it takes an item
        // number in integer form and a description in string form, and sets
        // the description to the item number.
		/* Example:
			setDescription(0, "Mountain Dew");
			This sets the description of Mountain Dew to item 0.
		*/
        Description[ItemNumber] = theDescription; // Set the description.
    } // END setDescription(int ItemNumber, String theDescription)

    public static String getDescription(int ItemNumber) {
        // This is again pretty self explanatory. It gets the description
        // given an integer item number.
		/* Example:
			String Desc = null;
			Desc = getDescription(0);
			This will return and set "Mountain Dew" to Desc.
		*/
        return Description[ItemNumber]; // Return the description.
    } // END getDescription(int ItemNumber)

    public TVM(int myBalance, boolean CloseAfter) { // This is the initiating function.
        // I have this set up to take an initial balance, if you dont want that just
        // enter 0 for myBalance when calling.
        balance = myBalance; // set the balance variable to whatever the initial
        // balance is.
        CA = CloseAfter;
        // This sets the CA private variable to the appropriate CloseAfter variable.
    } // END VM(int myBalance)

    public static boolean getCA() {
        return CA;
    }

    public static void setBalance(int Balance) {
        // This is pretty simple, and although not used, I figured that it could
        // be used later on if this program were to be improved upon.
        // It sets the balance to any integer (in cents)
		/* Example:
			setBalance(100);
			This sets the balance to $1.00
		*/
        balance = Balance; // Set the balance inputted to the public balance variable.
    } // END setBalance(int Balance)

    public static boolean BuyItem(int ItemNumber) {
        boolean returnMe; // This is the boolean value to be returned.
        // This buys an item from the inventory of the machine.
        // This is a little more complicated.

		/* Example:
			boolean x;
			x = BuyItem(0);
			This buys item number 0.
			If we have enough money, or the buy is successful, x is set to TRUE
			otherwise, it is set to false.
		*/
        // First we need to check if the balance that the user has is enough
        // to buy the item.
        if (balance < getPrice(ItemNumber)) {
            // This checks if the balance we have is less than the cost og
            // the item being bought. That item is indicated by the int
            // required as input.
            // Not enough money:
            Output("Not enough money!", 1);
			/* For this version of VM.java, we are using a graphical interface
			   so it is possible that the user does NOT want their change back just yet.
			   so we will incorporate this into the VMGUI.java where it checks this type
			   of thing.
			   See VMGUI.java for more details.
			*/
            // ReturnChange();
            // This tells the user that there is not enough money in
            // and returns their change.
            returnMe = false; // The buy was not successful.
        } // END if (balance < getPrice(ItemNumber))

        else { // This is what happens when there IS enough money!
            balance -= getPrice(ItemNumber); // This takes the amount of money
            // spent out of the users balance.
            Output("Bought " + getDescription(ItemNumber) + " for " + PrettyMoney(getPrice(ItemNumber)), 1); // Tells the user
            // that they bought the item by calling the getDescription function
            // with the item number that was requested
            // ReturnChange(); // This returns their change.
            // The above line is also removed in case the Close After value is false
            // meaning that the user doesnt want their money back yet, they have to
            // buy other things.
            returnMe = true; // The buy was successful, so we will want to return true.
        } // END else

        return returnMe; // This returns the value either true of false.
    } // END BuyItem(int ItemNumber)

    public static void PrintMenu() {
        // This function is pretty simple, it prints the menu using the designed "Output" function
        // which was created so that it would be easy to switch between the System.out.println
        // way of output to the JOptionPane way of conveying information.
		/*  Example:
			PrintMenu();
			This will print a pretty, organized, and super duper menu to the screen.
			It uses the array previously set up at the beginning of the class to
			keep values stored and accessible by number.
			This prints the following:(Prices tentative)
			1  Mountain Dew   $1.65     D  $1.00 (Dollar)
			2  Coke           $0.75     H  $0.50 (Half Dollar)
			3  Crab Juice     $0.35     Q  $0.25 (Quarter)
			4  Blood          $7.50     D  $0.10 (Dime)
			5  Lemonade       $1.25     N  $0.05 (Nickel)
			6  Pepsi          $1.05     C  Coin Return
		*/
        String myMenu =
                "1  " + getDescription(0) + "   " + PrettyMoney(getPrice(0)) + "    D  $1.00 (Dollar)\n" +
                        "2  " + getDescription(1) + "   " + PrettyMoney(getPrice(1)) + "    H  $0.50 (Half Dollar)\n" +
                        "3  " + getDescription(2) + "   " + PrettyMoney(getPrice(2)) + "    Q  $0.25 (Quarter)\n" +
                        "4  " + getDescription(3) + "   " + PrettyMoney(getPrice(3)) + "    M  $0.10 (Dime)\n" +
                        "5  " + getDescription(4) + "   " + PrettyMoney(getPrice(4)) + "    N  $0.05 (Nickel)\n" +
                        "6  " + getDescription(5) + "   " + PrettyMoney(getPrice(5)) + "    C  Coin Return";
        // This is the menu. Notice the nice spacing. I dont know how this will hold up in the
        // JOptionPane so for now I will stick with the System.out.println.
        Output(myMenu, 1); // This uses my output function to send it to the user.
    } // END PrintMenu()

    public static void Output(String Message, int Type) {
        // Here is the long awaited Output function!
        // This takes a message to output in string form, and a type of output
        // in integer form.
		/* Example:
			Output("Hello", 1);
			This outputs Hello by using System.out.println("Hello");
		*/
        if (Type == 1) {  // this checks the type of output
            // if it is type 1
            //System.out.println(Message); // then use the System.out.println function
            JOptionPane.showMessageDialog(null, Message, "Vending Machine", JOptionPane.PLAIN_MESSAGE);
        } // END if (Type == 1)
        else {
            // Output in JOptionPane
            // This is not implemented yet because I am afraid of the horrible formatting!
        } // END else
    } // END Output(String message, int Type)

    public static void ReturnChange() {
        // This is the beautiful ReturnChange function.
        // This is not that complicated, but I will explain it all:
		/* Example:
			ReturnChange();
			This calculates and outputs the amount of change to give to
			the user.
		*/
        // Declare variables.
        int myTotal = getBalance(); // Get the balance and set it to "myTotal"
        int Dollars = 0; // Dummy variables for how many Dollars,
        int HalfDollars = 0; // Half Dollars
        int Quarters = 0; // Quarters
        int Dimes = 0; // Dimes
        int Nickels = 0;  // Nickels

        // Now we start the actual calculations:
        Dollars = myTotal / 100; // This does integer division for the number of
        // dollars that will go into the total.
        // If the total is 5.50 and we want to get as many dollars as possible,
        // we can get 5 in there.
        myTotal -= (100 * Dollars); // Now to get the remainder, I found it was easiest
        // to avoid division by zero by simply removing the number of dollars, times 100
        // to get everything into cents.
        // We are actually doing 550 / 100 = 5
        // and calculating 550 - (5 * 100) to get the remainder which is 50.
        // The same thing is done here except we divide what is left by 50 to see how many
        // half dollars will fit in there, and then set that to the number of half dollars.
        HalfDollars = myTotal / 50;
        myTotal -= (50 * HalfDollars); // This here then again gets the remainder.
        Quarters = myTotal / 25; // Same thing again, find out if any quarters will fit.
        myTotal -= (25 * Quarters); // Get the remainder.
        Dimes = myTotal / 10; // Do it again with Dimes.
        myTotal -= (10 * Dimes); // Check for remainders.
        Nickels = myTotal / 5; // Do this one last time with Dimes.
        myTotal -= (5 * Nickels); // Take away the last remainder. This should be zero.

        if (!(myTotal == 0)) { // If the total remainder is not zero, that means that the user
            // somehow got pennies into the machine and broke it. Good job....
            Output("Something went very wrong! You need pennies! How did you get pennies in there?", 1);
            // Tell the user that they are stupid.
        } // END if (!(myTotal == 0))

        Output("Your change is: \n" +
                        Dollars + " Dollars\n" +
                        HalfDollars + " Half Dollars\n" +
                        Quarters + " Quarters\n" +
                        Dimes + " Dimes\n" +
                        Nickels + " Nickels\n" +
                        "For a total of " + PrettyMoney(getBalance())
                ,1); // This outputs the change to the user.
        // Lists out how many Dollars, half dollars, nickels, dimes, and quarters are
        // being spit out. This also gives a grand total at the end.
        setBalance(0); // Clear the balance.
        // See we CAN use this function!
    } // END ReturnChange()

    public static String getPrettyBalance() {
        // This gets the balance but turns it pretty for the user.
		/* Example:
			String x = null;
			x = getPrettyBalance();
			This returns the balance (say 100 cents) in the form of
			$1.00
		*/
        return PrettyMoney(balance); // Just return it prettified from the PrettyMoney function
    } // END getPrettyBalance()

    public static int getBalance() {
        // This simply returns the balance for the user to look at.
		/* Example:
			int x = 0;
			x = getBalance;
			This gets the balance in cents and sets it to x.
		*/
        return balance; // Return the balance.
    } // END getBalance()

    public static void AddMoney(int Amount) {
        // This adds money to the machine, and therefore adds money to the balance.
		/* Example:
			AddMoney(50);
			This adds 50 cents to the balance of the user.
		*/
        balance = balance + Amount; // Add the amount (in cents) to the balance.
    } // END AddMoney(int Amount)

    public static void Initialize() {
        // This "initializes" the machine so that the user doesnt have to
        // input their own drink names and prices.
        // This is easily editable which is nice, but it also allows for the user
        // to change drink names and prices if implemented into the menu system.
		/* Example:
			Initialize();
			This initializes the machine.
		*/
        int i = 0; // Used for the for loop later on.
        String Desc = null; // This is used for the Pretty Formatting
        // It holds the description of the current item as we scroll through
        // all of them.
        // The following statements set the descriptions of items 0 - 5 (1-6 for the user).
        setDescription(0, DESC_0);
        setDescription(1, DESC_1);
        setDescription(2, DESC_2);
        setDescription(3, DESC_3);
        setDescription(4, DESC_4);
        setDescription(5, DESC_5);
        // The following statements set the prices for these items.
        setPrice(0, PRICE_0);
        setPrice(1, PRICE_1);
        setPrice(2, PRICE_2);
        setPrice(3, PRICE_3);
        setPrice(4, PRICE_4);
        setPrice(5, PRICE_5);
        // This for loop is used to get the longest of all of the descriptions.
        for(i = 0 ; i < 6; i++) { // This goes through all of them:
            Desc = getDescription(i); // Gets the description of the current one.
            if (Desc.length() > MaxLen) { // If the descriptions length is longer than
                // the current maximum length, then we have a new maximum length.
                MaxLen = getDescription(i).length(); // Set the new max length.
            } // END if (Desc.length() > MaxLen)
        } // END (i = 0 ; i < 6; i++)
        FormatDescriptions(MaxLen); // Format all of the descriptions according to this new
        // longest length.
    } // END Initialize()

    public static void FormatDescriptions(int ML) {
        // This function formats all of the descriptions according to the longest length.
		/* Example:
			FormatDescription(20);
			This will take all the strings and add spaces to the end until they all
			have an length of 20 characters.
		*/
		/*int i = 0; // Used for the for loop later on.
		String Desc = null; // This stores the description of the current item.
		for(i = 0; i < 6 ; i++) { // This loop goes through all of the items and gets their descriptions.
			Desc = getDescription(i); // This sets the description to Desc so we can loop at it...
			while (Desc.length() < ML) { // As long as the Descriptions length is smaller than the
				// maximum length,
			 	Desc += " "; // We keep adding spaces to the end of it!
			} // END while(Desc.length() < ML)
			setDescription(i, Desc); // Set the description to the new LONGER description!
		} // END for(i = 0; i < 6 ; i++)
		*/
    } // END FormatDescription(int ML)

    /* Pretty Money
      Returns: String in form of $X.XX
      Inputs: Takes an integer in the form of XXX
      5 Variables involved.
    */
    public static String PrettyMoney (int Money) { // This is a version I wrote.
        int Dollars = 0; // This gives us the number for the first X position.
        // Is used for this place:  "$_.XX" (the _'s)
        int Cents = 0; // This gives us the number for the cents positions.
        // Is used for this place: "$X.__" (the _'s)
        int Tens = 0; // This is used for the tens place.
        // Is used for this place: "$X._X" (the _'s)
        int Ones = 0; // This is used for the ones place.
        // Is used for this place: "$X.X_" (the _'s)
        String Pretty = null; // This is the value returned in String form.
        // Do Dollar bills first:
        Dollars = Money / 100; // Returns how many dollars we have here.
        // Do the Cents!
        Cents = Money % 100; // This gets the remainer, or how many cents are left.
        // Now get the Ten's and One's places from this "Cents".
        Tens = Cents / 10; // This gets the tens place of our total.
        Ones = Cents % 10; // This gets the ones place of our total.
        // Create our pretty String that has the format: "$X.XX"
        Pretty = "$" + Dollars + "." + Tens + Ones;
        return Pretty; // Return our pretty string.
        /* Example
            String x = null;
            x = PrettyMoney(165);
                [This is for $1.65 which is how it should look]
                [165 cents = $1.65]
            Dollars = 165/100 = 1;
            Cents = 165%100 = 65;
            Tens = 65/10 = 6;
            Ones = 65%10 = 5;
            Pretty = "$1.65"
            return "$1.65"
            This sets "$1.65" to x.
        */
    } // END PrettyMoney (int Money)
} // End VM 