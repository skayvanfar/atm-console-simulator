package ir.sk.atm.service;

import ir.sk.atm.model.Account;
import ir.sk.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(Long id) {
        return accountRepository.findById(id).get(); // TODO: 10/8/21 get
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void credit(long accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        account.credit(amount);
    }

    public BigDecimal getAvailableBalance(long accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getAvailableBalance();
    }

    public void debit(long accountNumber, int amount) {
        Account account = getAccount(accountNumber);
        account.debit(BigDecimal.valueOf(amount));
    }
}
