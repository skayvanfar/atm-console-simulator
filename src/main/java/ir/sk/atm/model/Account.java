package ir.sk.atm.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_ATM_ACCOUNT")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PIN", nullable = false)
    private int pin; // PIN for authentication

    @Column(name = "available_Balance", nullable = false)
    private BigDecimal availableBalance; // available withdrawal amount

    @Column(name = "total_Balance", nullable = false)
    private BigDecimal totalBalance; // funds available + pending deposit

    // determines whether a user-specified PIN matches PIN in Account
    @Transient
    public boolean validatePIN(int userPIN) {
        return (userPIN == pin);
    }

    // credits the account (funds have not yet cleared)
    public void credit(BigDecimal amount) {
        totalBalance = totalBalance.add(amount); // add to total balance
    }

    // debits the account
    public void debit(BigDecimal amount) {
        availableBalance = availableBalance.subtract(amount); // subtract from available balance
        totalBalance = totalBalance.subtract(amount); // subtract from total balance
    }
}
