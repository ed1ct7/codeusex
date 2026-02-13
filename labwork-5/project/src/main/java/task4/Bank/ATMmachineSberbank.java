package task4.Bank;

import java.math.BigDecimal;

public class ATMmachineSberbank extends ATMmachine {
    public ATMmachineSberbank() {
        enterInterface();
        bank = Bank.Sberbank;
        comission = new BigDecimal(0.00);
    }
}
