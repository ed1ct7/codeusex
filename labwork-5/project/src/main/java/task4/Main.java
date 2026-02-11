package task4;

import task4.Bank.ATMmachineMyBank;

public class Main {
    public static void main(String[] args) {
        DBManager.getInstance();
        new ATMmachineMyBank();
    }
}
