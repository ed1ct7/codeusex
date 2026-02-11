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
                String phoneNumber, String seriesAndNumber, String password){
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.seriesAndNumber = seriesAndNumber;
        this.password = password;
    }

    public User(int id, String name, String surname, String secondName, String address,
                String phoneNumber, String seriesAndNumber, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.seriesAndNumber = seriesAndNumber;
        this.password = password;
    }

    //    CREATE TABLE IF NOT EXISTS User (
    //            id INTEGER PRIMARY KEY AUTOINCREMENT,
    //            name TEXT NOT NULL,
    //            surname TEXT NOT NULL,
    //            secondName TEXT,
    //            address TEXT NOT NULL,
    //            phoneNumber TEXT NOT NULL,
    //            seriesAndNumber TEXT NOT NULL UNIQUE
    //    );

    public User(){}

    public int getId() { return id; }

    public String getName() { return name; }

    public String getSurname() { return surname; }
}
