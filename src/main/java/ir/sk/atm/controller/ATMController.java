package ir.sk.atm.controller;


import ir.sk.atm.others.MenuOption;
import ir.sk.atm.transiction.Deposit;
import ir.sk.atm.transiction.Transaction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/atm")
public class ATMController {

    Deposit deposit;

    @PostMapping(value = "/deposit")
    public void deposit(@RequestParam("amount") int amount) {
        Transaction.newInstance(MenuOption.DEPOSIT, 10).execute();
    }

    @PostMapping(value = "/withdraw")
    public int withdraw() {
        return 0;
    }

    @GetMapping("/checkBalance")
    public int checkBalance() {
        return 0;
    }
}
