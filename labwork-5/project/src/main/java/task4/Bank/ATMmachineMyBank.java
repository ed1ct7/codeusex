package task4.Bank;

import task4.Interfaces.IATMmachine;
import task4.User.BankAccount;
import task4.User.BankAccountRepository;
import task4.User.User;
import task4.User.UserRepository;

import java.util.List;
import java.util.Scanner;

public class ATMmachineMyBank implements IATMmachine {
    private final Scanner sc = new Scanner(System.in);
    private User currentUser;
    private BankAccount currentAccount;

    public ATMmachineMyBank() {
        enterInterface();
    }

    private void enterInterface() {
        while (true) {
            System.out.println("Выберите опцию:\n1 - Создать пользователя\n2 - Войти в пользователя");
            int choose = readInt();

            if (choose == 1) {
                createUser();
            } else if (choose == 2) {
                enterUser();
            }
        }
    }

    private void createUser() {
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

        System.out.println("ПОЛЬЗОВАТЕЛЬ " + name + " ДОБАВЛЕН");
        System.out.println("=======================");
    }

    private void enterUser() {
        sc.nextLine();
        System.out.println("=======================");
        System.out.println("ВХОД В АККАУНТ ПОЛЬЗОВАТЕЛЯ");

        System.out.println("Введите серию и паспорт аккаунта: ");
        String seriesAndNumber = sc.nextLine();

        System.out.println("Введите пароль аккаунта: ");
        String password = sc.nextLine();

        UserRepository ur = new UserRepository();
        User user = ur.TryEnterAccount(seriesAndNumber, password);

        if (user == null) {
            System.out.println("ВХОД НЕВЫПОЛНЕН, ВВЕДИТЕ КОРРЕКТНЫЕ ДАННЫЕ");
            System.out.println("=======================");
            return;
        }

        currentUser = user;
        System.out.println("ВХОД СОВЕРШЕН");
        System.out.println("=======================");
        userInterface();
    }

    private void userInterface() {
        while (true) {
            System.out.println("\n=======================");
            System.out.println("Аккаунт " + currentUser.getName());
            System.out.println("Выберите опцию:\n1 - Создать банковский аккаунт\n2 - Войти в аккаунт\n3 - Выйти");

            int choose = readInt();
            if (choose == 1) {
                createAccount();
            } else if (choose == 2) {
                enterAccount();
            } else if (choose == 3) {
                currentUser = null;
                return;
            }
        }
    }

    private void createAccount() {
        System.out.println("=======================");
        System.out.println("ДОБАВЛЕНИЕ БАНКОВСКОГО АККАУНТА");
        sc.nextLine();

        System.out.println("Введите название вклада: ");
        String contributionName = sc.nextLine();

        System.out.println("Введите название банка (MyBank или другой): ");
        String bankName = sc.nextLine();

        System.out.println("Задайте pin аккаунту: ");
        int pin = readInt();

        BankAccount bankAccount = new BankAccount(contributionName, 0, currentUser, pin, bankName);
        BankAccountRepository bankAccountRepository = new BankAccountRepository();
        bankAccountRepository.InsertMethod(bankAccount);

        System.out.println("АККАУНТ ДОБАВЛЕН");
        System.out.println("=======================");
    }

    private void enterAccount() {
        System.out.println("\n=======================");
        System.out.println("БАНКОВСКИЕ АККАУНТЫ ПОЛЬЗОВАТЕЛЯ");

        UserRepository userRepository = new UserRepository();
        List<BankAccount> bankAccountsList = userRepository.GetConnectedBankAccounts(currentUser);

        if (bankAccountsList.isEmpty()) {
            System.out.println("Нет банковских аккаунтов");
            return;
        }

        for (int i = 0; i < bankAccountsList.size(); i++) {
            BankAccount bankAccount = bankAccountsList.get(i);
            System.out.println(i + " - id " + bankAccount.getId() + " | " + bankAccount.getContributionName() + " | " + bankAccount.getBankName());
        }

        System.out.println("Выберите аккаунт: ");
        int choice = readInt();

        if (choice < 0 || choice >= bankAccountsList.size()) {
            System.out.println("Неверный выбор");
            return;
        }

        currentAccount = bankAccountsList.get(choice);
        System.out.println("Успешный вход в аккаунт");
        bankAccountInterface();
    }

    private void bankAccountInterface() {
        while (true) {
            BankAccountRepository repository = new BankAccountRepository();
            currentAccount = repository.GetById(currentAccount.getId());

            System.out.println("\n=======================");
            System.out.println("Счёт id " + currentAccount.getId() + ", банк: " + currentAccount.getBankName());
            System.out.println("1 - Узнать баланс");
            System.out.println("2 - Пополнить счёт");
            System.out.println("3 - Снять деньги");
            System.out.println("4 - Перевести деньги");
            System.out.println("5 - Назад");

            int choose = readInt();

            if (choose == 1) {
                System.out.println("Баланс: " + checkBalance());
            } else if (choose == 2) {
                System.out.println("Сумма пополнения: ");
                float money = readFloat();
                replenishBalance(money);
            } else if (choose == 3) {
                System.out.println("Сумма снятия: ");
                float money = readFloat();
                float result = withdrawBalance(money);
                if (result >= 0) {
                    System.out.println("Снято: " + result);
                }
            } else if (choose == 4) {
                System.out.println("Введите id счёта получателя: ");
                int id = readInt();
                System.out.println("Сумма перевода: ");
                float money = readFloat();
                float result = makeTransaction(id, money);
                if (result >= 0) {
                    System.out.println("Переведено: " + result);
                }
            } else if (choose == 5) {
                currentAccount = null;
                return;
            }
        }
    }

    private boolean checkPin() {
        System.out.println("Введите pin: ");
        int enteredPin = readInt();
        if (enteredPin != currentAccount.getPin()) {
            System.out.println("Неверный pin");
            return false;
        }
        return true;
    }

    @Override
    public float checkBalance() {
        if (!checkPin()) {
            return -1;
        }

        BankAccountRepository repository = new BankAccountRepository();
        currentAccount = repository.GetById(currentAccount.getId());
        return currentAccount.getBalance();
    }

    @Override
    public void replenishBalance(float money) {
        if (money <= 0) {
            System.out.println("Сумма должна быть больше 0");
            return;
        }

        if (!checkPin()) {
            return;
        }

        BankAccountRepository repository = new BankAccountRepository();
        BankAccount account = repository.GetById(currentAccount.getId());
        repository.UpdateBalance(account.getId(), account.getBalance() + money);
        currentAccount = repository.GetById(currentAccount.getId());
        System.out.println("Баланс после пополнения: " + currentAccount.getBalance());
    }

    @Override
    public float withdrawBalance(float money) {
        if (money <= 0) {
            System.out.println("Сумма должна быть больше 0");
            return -1;
        }

        if (!checkPin()) {
            return -1;
        }

        BankAccountRepository repository = new BankAccountRepository();
        BankAccount account = repository.GetById(currentAccount.getId());

        float total = money;
        if (!"MyBank".equalsIgnoreCase(account.getBankName())) {
            total = money + money * 0.02f;
        }

        if (account.getBalance() < total) {
            System.out.println("Недостаточно средств");
            return -1;
        }

        repository.UpdateBalance(account.getId(), account.getBalance() - total);
        currentAccount = repository.GetById(currentAccount.getId());
        System.out.println("Баланс после снятия: " + currentAccount.getBalance());
        return money;
    }

    @Override
    public float makeTransaction(int id, float money) {
        if (money <= 0) {
            System.out.println("Сумма должна быть больше 0");
            return -1;
        }

        if (!checkPin()) {
            return -1;
        }

        BankAccountRepository repository = new BankAccountRepository();
        BankAccount from = repository.GetById(currentAccount.getId());
        BankAccount to = repository.GetById(id);

        if (to == null) {
            System.out.println("Счёт получателя не найден");
            return -1;
        }

        if (from.getBalance() < money) {
            System.out.println("Недостаточно средств");
            return -1;
        }

        repository.UpdateBalance(from.getId(), from.getBalance() - money);
        repository.UpdateBalance(to.getId(), to.getBalance() + money);
        currentAccount = repository.GetById(currentAccount.getId());
        System.out.println("Баланс после перевода: " + currentAccount.getBalance());
        return money;
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
        return sc.nextInt();
    }

    private float readFloat() {
        while (!sc.hasNextFloat()) {
            sc.next();
        }
        return sc.nextFloat();
    }
}
