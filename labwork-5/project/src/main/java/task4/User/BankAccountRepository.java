package task4.User;

import task4.DBManager;
import task4.Interfaces.IRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountRepository extends BankAccount implements IRepository <BankAccount>{

    @Override
    public void InsertMethod(BankAccount bankAccount) {

        var conn = DBManager.getInstance().getConnection();

//        CREATE TABLE IF NOT EXISTS BankAccount (
//                id INTEGER PRIMARY KEY AUTOINCREMENT,
//        contributionName TEXT NOT NULL DEFAULT 'Standard',
//                balance REAL DEFAULT 0,
//                userId INTEGER NOT NULL,
//                bankName TEXT NOT NULL,
//                pin INT NOT NULL,
//                FOREIGN KEY (userId) REFERENCES User(id)
//        )

        String str = "INSERT INTO BankAccount (contributionName, userId, bankName, pin)" +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement pst = conn.prepareStatement(str)){
            pst.setString(1, bankAccount.contributionName);
            pst.setInt(2, bankAccount.userId);
            pst.setString(3, bankAccount.BankName);
            pst.setInt(4,bankAccount.pin);
            pst.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public BankAccount TryEnterBankAccount(BankAccount bankAccount, int pin){
        if(pin == bankAccount.pin){
            return bankAccount;
        }else {
            return null;
        }
    }
}
