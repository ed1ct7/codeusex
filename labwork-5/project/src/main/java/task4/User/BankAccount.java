package task4.User;

public class BankAccount {
    int id;
    String contributionName;
    float balance;
    int userId;
    int pin;
    String bankName;

    public BankAccount(int id, String contributionName, float balance, int userId, int pin, String bankName) {
        this.id = id;
        this.contributionName = contributionName;
        this.balance = balance;
        this.userId = userId;
        this.pin = pin;
        this.bankName = bankName;
    }

    public BankAccount(String contributionName, float balance, User user, int pin, String bankName) {
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

    public float getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public int getPin() {
        return pin;
    }

    public String getBankName() {
        return bankName;
    }

    public String getContributionName() {
        return contributionName;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
