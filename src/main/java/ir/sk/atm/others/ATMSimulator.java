package ir.sk.atm.others;

import ir.sk.atm.model.Account;
import ir.sk.atm.service.AccountService;
import ir.sk.atm.transiction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ATMSimulator {

    private boolean userAuthenticated; // true if user is authenticated
    private int currentAccountNumber; // user's account number

    /*    @Autowired
        private CashDispenser cashDispenser; // ref to ATM's cash dispenser
        @Autowired
        private DepositSlot depositSlot; // reference to ATM's deposit slot
        @Autowired
        private BankDatabase bankDatabase; // ref to account info database*/
    @Autowired
    private Screen screen; // reference to ATM's screen
    @Autowired
    private Keypad keypad; // reference to ATM's keypad

    @Autowired
    private AccountService accountService;

    public ATMSimulator() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
    }

    // start ATM
    public void run() {
        // welcome and authenticate user; perform transactions
        while (true) {

            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser(); // authenticate user
            }

            Account user = new Account();
            user.setPin(1234);
            user.setAvailableBalance(BigDecimal.ONE);
            user.setTotalBalance(BigDecimal.ONE);
            accountService.save(user);

            performTransactions(); // user is now authenticated
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
        } // end while
    } // end method run

    // attempt to authenticate user against database
    private void authenticateUser() {
        // prompt for account number and input it from user
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput();

        // prompt for PIN and input it from user
        screen.displayMessage("\nEnter your PIN: ");
        int pin = keypad.getInput();

        // set userAuthenticated to boolean value returned by database
        // todo  userAuthenticated = use.AuthenticateUser(accountNumber, pin);

        userAuthenticated = true;
        // check whether authentication succeeded
        if (userAuthenticated)
            currentAccountNumber = accountNumber; // save user's account #
        else
            screen.displayMessageLine(
                    "Invalid account number or PIN. Please try again.");
    }

    // display the main menu and perform transactions
    private void performTransactions() {
        Transaction currentTransaction; // transaction being processed
        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen exit option
        while (!userExited) {
            // show main menu and get user selection
            MenuOption menuOption = DisplayMainMenu();

            // decide how to proceed based on user's menu selection
            switch (menuOption) {
                // user chooses to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    // initialize as new object of chosen type
                    currentTransaction = Transaction.newInstance(menuOption, currentAccountNumber);
                    currentTransaction.execute(); // execute transaction
                    break;
                case EXIT_ATM: // user chose to terminate session
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true; // this ATM session should end
                    break;
                default: // user did not enter an integer from 1-4
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                    break;
            }
        }

    }

    // display the main menu and return an input selection
    private MenuOption DisplayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
        screen.displayMessageLine("Enter a choice: ");
        return MenuOption.valueOf(keypad.getInput()); // return user's selection
    }

}
