package task4;

import task4.Bank.ATMmachine;
import task4.Bank.ATMmachineSberbank;

public class Main {
    public static void main(String[] args) {
//        float tempf = 123.75432f;
//        int tempi = (int) tempf;
//        System.out.println(tempi);
          DBManager.getInstance();
          new ATMmachineSberbank();
    }
}
