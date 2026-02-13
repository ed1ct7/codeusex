package task4.User;

import task4.DBManager;
import task4.Interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends User implements IRepository <User>{

    //    CREATE TABLE IF NOT EXISTS User (
    //            id INTEGER PRIMARY KEY AUTOINCREMENT,
    //            name TEXT NOT NULL,
    //            surname TEXT NOT NULL,
    //            secondName TEXT,
    //            address TEXT NOT NULL,
    //            phoneNumber TEXT NOT NULL,
    //            seriesAndNumber TEXT NOT NULL UNIQUE
    //    );


    @Override
    public void InsertMethod(User user){
        var conn = DBManager.getInstance().getConnection();
        String str = "INSERT INTO User (name, surname, secondName, address, phoneNumber, seriesAndNumber, password)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pst = conn.prepareStatement(str)){
            pst.setString(1, user.name);
            pst.setString(2, user.surname);
            pst.setString(3, user.secondName);
            pst.setString(4, user.address);
            pst.setString(5, user.phoneNumber);
            pst.setString(6, user.seriesAndNumber);
            pst.setString(7, user.password);
            pst.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        };
    }

    public User TryEnterAccount(String seriesAndNumber, String password) {
        var conn = DBManager.getInstance().getConnection();
        String sql = "SELECT * FROM User WHERE seriesAndNumber = ? AND password = ?";

        try(PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, seriesAndNumber);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("secondName"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getString("seriesAndNumber"),
                        rs.getString("password")
                );
                System.out.println("Создан объект: " + user.getName() + " " + user.getSurname());
                return user;
            } else {
                System.out.println("Пользователь НЕ найден в БД");
                return null;
            }
        }
        catch (SQLException e){
            System.out.println("Ошибка SQL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


//        CREATE TABLE IF NOT EXISTS BankAccount (
//                id INTEGER PRIMARY KEY AUTOINCREMENT,
//        contributionName TEXT NOT NULL DEFAULT 'Standard',
//                balance REAL DEFAULT 0,
//                userId INTEGER NOT NULL,
//                bankName TEXT NOT NULL,
//                pin INT NOT NULL,
//                FOREIGN KEY (userId) REFERENCES User(id)
//        )

    public List<BankAccount> GetConnectedBankAccounts(User user){
        List<BankAccount> BankAccounts = new ArrayList<>();
        var conn = DBManager.getInstance().getConnection();

        String sql = "SELECT * FROM BankAccount WHERE userId = ?";

        try(PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                BankAccount bankAccount = new BankAccount(
                        rs.getInt("id"),
                        rs.getString("contributionName"),
                        rs.getBigDecimal("balance"),
                        rs.getInt("userId"),
                        rs.getInt("pin"),
                        rs.getString("bankName")
                        );
                BankAccounts.add(bankAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BankAccounts;
    }
}
