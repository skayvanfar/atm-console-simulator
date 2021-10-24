package ir.sk.atm.others;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class Keypad {
    private Scanner input; // reads data from the command line

    // no-argument constructor initializes the Scanner
    public Keypad() {
        input = new Scanner(System.in);
    } // end no-argument Keypad constructor

    // return an integer value entered by user
    public int getInput() {
        return input.nextInt(); // we assume that user enters an integer
    }

    public BigDecimal getValue() {
        return input.nextBigDecimal();
    }
}
