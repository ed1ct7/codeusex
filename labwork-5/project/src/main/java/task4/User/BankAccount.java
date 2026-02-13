package task4.User;

import task4.Bank.Bank;

import java.math.BigDecimal;

public class BankAccount {
    int id;
    String contributionName;
    BigDecimal balance;
    int userId;
    int pin;
    Bank bankName;

    public BankAccount(int id, String contributionName, BigDecimal balance, int userId, int pin, Bank bankName) {
        this.id = id;
        this.contributionName = contributionName;
        this.balance = balance;
        this.userId = userId;
        this.pin = pin;
        this.bankName = bankName;
    }

    public BankAccount(String contributionName, BigDecimal balance, User user, int pin, Bank bankName) {
        this.contributionName = contributionName;
        this.balance = balance;
        this.userId = user.getId();
        this.pin = pin;
        this.bankName = bankName;
    }

    public BankAccount() {
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public int getPin() {
        return pin;
    }

    public Bank getBankName() {
        return bankName;
    }

    public String getContributionName() {
        return contributionName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
