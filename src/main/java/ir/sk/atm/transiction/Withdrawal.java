package ir.sk.atm.transiction;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class Withdrawal extends Transaction {

    private int amount; // amount to deposit

    // constant representing cancel option
    private final static int CANCELED = 6;

    public Withdrawal(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public void execute() {
        boolean cashDispensed = false; // cash was not dispensed yet

        // transaction was not canceled yet
        boolean transactionCanceled = false;

        // loop until cash is dispensed or the user cancels
        do {
            // obtain the chosen withdrawal amount from the user
            int selection = displayMenuOfAmounts();

            // check whether user chose a withdrawal amount or canceled
            if (selection != CANCELED) {
                // set amount to the selected dollar amount
                amount = selection;

                // get available balance of account involved
                BigDecimal availableBalance = accountService.getAvailableBalance(accountNumber);

                // check whether the user has enough money in the account
                if (BigDecimal.valueOf(amount).compareTo(availableBalance) <= 0) {
                    // check whether the cash dispenser has enough money
                    if (cashDispenser.isSufficientCashAvailable(amount)) {
                        // debit the account to reflect the withdrawal
                        accountService.debit(accountNumber, amount);

                        cashDispenser.DispenseCash(amount); // dispense cash
                        cashDispensed = true; // cash was dispensed

                        // instruct user to take cash
                        userScreen.displayMessageLine(
                                "\nPlease take your cash from the cash dispenser.");
                    } else // cash dispenser does not have enough cash
                        userScreen.displayMessageLine(
                                "\nInsufficient cash available in the ATM." +
                                        "\n\nPlease choose a smaller amount.");
                } else // not enough money available in user's account
                    userScreen.displayMessageLine(
                            "\nInsufficient cash available in your account." +
                                    "\n\nPlease choose a smaller amount.");
            } else {
                userScreen.displayMessageLine("\nCanceling transaction...");
                transactionCanceled = true; // user canceled the transaction
            }
        } while ((!cashDispensed) && (!transactionCanceled));
    }

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 6 if the user chooses to cancel
    private int displayMenuOfAmounts() {
        int userChoice = 0; // variable to store return value

        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};

        // loop while no valid choice has been made
        while (userChoice == 0) {
            // display the menu
            userScreen.displayMessageLine("\nWithdrawal options:");
            userScreen.displayMessageLine("1 - $20");
            userScreen.displayMessageLine("2 - $40");
            userScreen.displayMessageLine("3 - $60");
            userScreen.displayMessageLine("4 - $100");
            userScreen.displayMessageLine("5 - $200");
            userScreen.displayMessageLine("6 - Cancel transaction");
            userScreen.displayMessage("\nChoose a withdrawal option (1-6): ");

            // get user input through keypad
            int input = keypad.getInput();

            // determine how to proceed based on the input value
            switch (input) {
                // if the user chose a withdrawal amount (i.e., option
                // 1, 2, 3, 4, or 5), return the corresponding amount 
                // from the amounts array
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input]; // save user's choice
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default:
                    userScreen.displayMessageLine(
                            "\nInvalid selection. Try again.");
                    break;
            }
        }

        return userChoice;
    }
}
