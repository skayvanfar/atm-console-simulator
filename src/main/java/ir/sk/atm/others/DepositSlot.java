package ir.sk.atm.others;

import org.springframework.stereotype.Service;

@Service
public class DepositSlot {
    public boolean isDepositEnvelopeReceived() {
        return true; // deposit envelope was received
    }
}
