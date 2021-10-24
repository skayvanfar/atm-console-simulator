package ir.sk.atm.others;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CashDispenser {

    // the default initial number of bills in the cash dispenser
    private static final int INITIAL_COUNT = 500;
    private int billCount; // number of $20 bills remaining

    // parameterless constructor initializes billCount to INITIAL_COUNT
    public CashDispenser()
    {
        billCount = INITIAL_COUNT; // set billCount to INITIAL_COUNT
    }

    // simulates dispensing the specified amount of cash
    public void DispenseCash(int amount)
    {
        // number of $20 bills required
        int billsRequired = ((int) amount) / 20;
        billCount -= billsRequired;
    }

    // indicates whether cash dispenser can dispense desired amount
    public boolean isSufficientCashAvailable(int amount)
    {
        // number of $20 bills required
        int billsRequired = ((amount) / 20);

        // return whether there are enough bills available
        return (billCount >= billsRequired);
    }
}
