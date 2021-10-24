package ir.sk.atm.transiction;

import ir.sk.atm.others.*;
import ir.sk.atm.repository.AccountRepository;
import ir.sk.atm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public abstract class Transaction {

    protected int accountNumber; // account involved in the transaction

    @Autowired
    protected Screen userScreen; // reference to ATM's screen

    @Autowired
    protected Keypad keypad; // reference to ATM's screen

    @Autowired
    protected DepositSlot depositSlot;

    @Autowired
    protected CashDispenser cashDispenser;

    @Autowired
    protected AccountService accountService; // reference to account info database

    public Transaction(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // static factory method
    public static Transaction newInstance(MenuOption type, int accountNumber) {
        Transaction temp = null; // null Transaction reference

        // determine which type of Transaction to create
        switch (type) {
            // create new BalanceInquiry transaction
           /* case MenuOption.BALANCE_INQUIRY:
                temp = new BalanceInquiry(accountNumber);
                break;*/
            case WITHDRAWAL: // create new Withdrawal transaction
                temp = new Withdrawal(accountNumber);
                break;
            case DEPOSIT: // create new Deposit transaction
                temp = new Deposit(accountNumber);
                break;
        }

        return temp;
    }

    public abstract void execute();
}
