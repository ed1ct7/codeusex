package task4.Bank;

//Задание 4. Создать класс Пользователь с полями Фамилия, имя, id, pin, поле с типом класса Счёт.
// Создать класс Счёт с полями название(вклада), баланс, поле с типом класса Пользователь, название банка.
// Создать интерфейс банкомат, в котором описаны следующие методы:
//        	узнать баланс,
//        	пополнить счёт,
//        	снять деньги со счета,
//        	перевести деньги с одного счета на другой.
//        При выполнении всех операций реализовывается проверка pin. При снятии и переводе денег необходимо учесть,
//        достаточно ли средств. В основной программе(или в отдельном файле) создать класс Банкомат банка MyBank,
//        реализующий интерфейс банкомат. В этом классе необходимо представить реализацию всех методов интерфейса
//        Банкомат. При снятии денег со счета необходимо сделать проверку, является ли клиент банкомата клиентом
//        банка MyBank. Если да, то при снятии денег комиссии на операцию нет, если нет, то комиссия 2 % от суммы.
//        При выполнении задания можно добавлять в классы дополнительные поля.
//        В основной программе показать взаимодействие созданных классов. Для некоторых операций необходимо
//        создать списки объектов классов.
//

import task4.Interfaces.IATMmachine;
import task4.User.BankAccount;
import task4.User.BankAccountRepository;
import task4.User.User;
import task4.User.UserRepository;

import java.util.List;
import java.util.Scanner;

public class ATMmachineMyBank implements IATMmachine {
    public ATMmachineMyBank(){
        EnterInterface();
    }

    Scanner sc = new Scanner(System.in);
    private int choose;
    private User currentUser;

    private void EnterInterface(){
        while(true){
            System.out.println("Выберите опцию: \n" +
                    "1 - Создать пользователя\n" +
                    "2 - Войти в пользователя");

            while (!sc.hasNextInt()) {
                sc.next();
            }
            choose = sc.nextInt();

            switch (choose){
                case 1:
                    CreateUser();
                    break;
                case 2:
                    EnterUser();
                    break;
            }
        }
    }

    private void CreateUser(){
        System.out.println("=======================");
        System.out.println("ДОБАВЛЕНИЕ ПОЛЬЗОВАТЕЛЯ");
        sc.nextLine();
        System.out.println("Введите имя: ");
        String name = sc.nextLine();
        System.out.println("Введите фамилию: ");
        String surname = sc.nextLine();
        System.out.println("Введите отчество: ");
        String secondName = sc.nextLine();
        System.out.println("Введите адрес проживания: ");
        String address = sc.nextLine();
        System.out.println("Введите номер телефона: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Введите серию и номер паспорта: ");
        String seriesAndNumber = sc.nextLine();
        System.out.println("Задайте пароль аккаунту: ");
        String password = sc.nextLine();
        UserRepository ur = new UserRepository();
        User user = new User(name, surname, secondName, address, phoneNumber, seriesAndNumber, password);
        ur.InsertMethod(user);
        System.out.println("ПОЛЬЗОВАТЕЛЬ " + user.getName() + " ДОБАВЛЕН");
        System.out.println("=======================");
    }

    private void EnterUser(){
        sc.nextLine();
        System.out.println("=======================");
        System.out.println("ВХОД В АККАУНТ ПОЛЬЗОВАТЕЛЯ");

        System.out.println("Введите серию и паспорт аккаунта: ");
        String seriesAndNumber = sc.nextLine();

        System.out.println("Введите пароль аккаунта: ");
        String password = sc.nextLine();

        UserRepository ur = new UserRepository();

        if(ur.TryEnterAccount(seriesAndNumber, password) == null){
            System.out.println("ВХОД НЕВЫПОЛНЕН, ВВЕДИТЕ КОРЕКТНЫЕ ДАННЫЕ");
            System.out.println("=======================");
        } else {
            User user = ur.TryEnterAccount(seriesAndNumber, password);
            currentUser = user;
            System.out.println("ВХОД СОВЕРШЕН");
            System.out.println("=======================");
            UserInterface();
        }
    }

    private void UserInterface(){;
        System.out.println("\n=======================");
        System.out.println("Аккаунт " + currentUser.getName());

        while(true){
            System.out.println("Выберите опцию: \n" +
                    "1 - Создать банковский аккаунт\n" +
                    "2 - Войти в аккаунт");

            while (!sc.hasNextInt()) {
                sc.next();
            }
            choose = sc.nextInt();

            switch (choose){
                case 1:
                    CreateAccount();
                    break;
                case 2:
                    EnterAccount();
                    break;
            }
        }
    }

    private void CreateAccount(){
        System.out.println("=======================");
        System.out.println("ДОБАВЛЕНИЕ БАНКОВСКОГО АККАУНТА");
        sc.nextLine();
        System.out.println("Задайте pin аккаунту: ");
        int pin = sc.nextInt();
        BankAccount bankAccount = new BankAccount("Стандарт", 0, currentUser, pin, "Сбербанк");
        BankAccountRepository bankAccountRepository = new BankAccountRepository();
        bankAccountRepository.InsertMethod(bankAccount);

//            public BankAccount(int id, String contributionName, float balance, int userId, int pin, String BankName){
//            this.id = id;
//            this.contributionName = contributionName;
//            this.balance = balance;
//            this.userId = userId;
//            this.pin = pin;
//            this.BankName = BankName;
//        }


        System.out.println("АККАУНТ ДОБАВЛЕН");
        System.out.println("=======================");
    }

    private void EnterAccount(){
        System.out.println("\n=======================");
        System.out.println("БАНКОВСКИЕ АККАУНТЫ ПОЛЬЗОВАТЕЛЯ");
        sc.nextLine();
        UserRepository userRepository = new UserRepository();
        List<BankAccount> bankAccountsList = userRepository.GetConnectedBankAccounts(currentUser);
        int temp = 0;
        for(BankAccount bankAccount : bankAccountsList){
            System.out.println("id: " + bankAccount.getId() + "\t: " + temp);
            temp++;
        }
        System.out.println("Выберите аккаунт: ");
        int choice = sc.nextInt();

        System.out.println("Введите pin: ");
        int pin = sc.nextInt();

        BankAccountRepository bankAccountRepository = new BankAccountRepository();
        if(bankAccountRepository.TryEnterBankAccount(bankAccountsList.get(choice), pin) == null){
            System.out.println("Не получилось войти в аккаунт (; Попробуйте ещё раз\n");
        } else {
           System.out.println("Успешный вход в аккаунт\n");
           BankAccountInterface();
        }
    }

    private void BankAccountInterface(){
        System.out.println("\n=======================");
        System.out.println("БАНКОВСКИЙ АККАУНТ ПОЛЬЗОВАТЕЛЯ " + currentUser.getName());

    }

    @Override
    public float checkBalance(){
        return 0;
    }
    @Override
    public void replenishBalance(float money){ }
    @Override
    public float withdrawBalance(float money){
        return 0;
    }
    @Override
    public float makeTransaction(int id, float money){
        return 0;
    }
}
