package ir.sk.atm.others;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Screen {

    // displays a message without a carriage return
    public void displayMessage(String message) {
        System.out.print(message);
    } // end method displayMessage

    // display a message with a carriage return
    public void displayMessageLine(String message) {
        System.out.println(message);
    } // end method displayMessageLine

    // display a dollar amount
    public void displayDollarAmount(BigDecimal amount) {
        System.out.printf("$%,.2f", amount);
    } // end method displayDollarAmount
}
