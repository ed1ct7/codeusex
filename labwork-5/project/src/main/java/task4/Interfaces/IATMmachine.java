package task4.Interfaces;

import java.math.BigDecimal;

public interface IATMmachine {
    public BigDecimal checkBalance();
    public void replenishBalance(BigDecimal money);
    public BigDecimal withdrawBalance(BigDecimal money);
    public BigDecimal makeTransaction(int id, BigDecimal money);
}
