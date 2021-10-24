package ir.sk.atm.transiction;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class Deposit extends Transaction {

    private BigDecimal amount; // amount to deposit

    // constant representing cancel option
    private final static BigDecimal CANCELED = BigDecimal.valueOf(0);

    public Deposit(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public void execute() {
        amount = PromptForDepositAmount(); // get deposit amount from user
        // check whether user entered a deposit amount or canceled
        if (amount != CANCELED) {
            // request deposit envelope containing specified amount
            userScreen.displayMessage("\nPlease insert a deposit envelope containing ");
            userScreen.displayDollarAmount(amount);
            userScreen.displayMessageLine(" in the deposit slot.");

            // retrieve deposit envelope
            boolean envelopeReceived = depositSlot.isDepositEnvelopeReceived();

            // check whether deposit envelope was received
            if (envelopeReceived) {
                userScreen.displayMessageLine(
                        "\nYour envelope has been received.\n" +
                                "The money just deposited will not be available " +
                                "until we \nverify the amount of any " +
                                "enclosed cash, and any enclosed checks clear.");

                // credit account to reflect the deposit
                accountService.credit(accountNumber, amount);
            } else
                userScreen.displayMessageLine("\nYou did not insert an envelope, so the ATM has canceled your transaction.");
        } else
            userScreen.displayMessageLine("\nCanceling transaction...");
    }

    // prompt user to enter a deposit amount to credit
    private BigDecimal PromptForDepositAmount() {
        // display the prompt and receive input
        userScreen.displayMessage("\nPlease input a deposit amount in CENTS (or 0 to cancel): ");
        BigDecimal input = keypad.getValue();

        // check whether the user canceled or entered a valid amount
        if (input == CANCELED)
            return input;
        else
            return input;
    }
}
