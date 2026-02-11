package task4.User;

public class BankAccount {
    int id;
    String contributionName;
    float balance;
    int userId;
    int pin;
    String BankName;

    public int getId(){
        return id;
    }

    public BankAccount(int id, String contributionName, float balance, int userId, int pin, String BankName){
        this.id = id;
        this.contributionName = contributionName;
        this.balance = balance;
        this.userId = userId;
        this.pin = pin;
        this.BankName = BankName;
    }

    public BankAccount(String contributionName, float balance, User user, int pin, String BankName){
        this.id = id;
        this.contributionName = contributionName;
        this.balance = balance;
        this.userId = user.getId();
        this.pin = pin;
        this.BankName = BankName;
    }

    public BankAccount(){}
}
