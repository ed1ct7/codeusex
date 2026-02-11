package task4.User;

public class User {
    int id;
    String name;
    String surname;
    String secondName;
    String address;
    String phoneNumber;
    String seriesAndNumber;
    String password;

    public User(String name, String surname, String secondName, String address,
                String phoneNumber, String seriesAndNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.seriesAndNumber = seriesAndNumber;
        this.password = password;
    }

    public User(int id, String name, String surname, String secondName, String address,
                String phoneNumber, String seriesAndNumber, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.seriesAndNumber = seriesAndNumber;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}
