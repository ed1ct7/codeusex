package task4.User;

import task4.Bank.Bank;
import task4.DBManager;
import task4.Interfaces.IRepository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRepository extends BankAccount implements IRepository<BankAccount> {

    @Override
    public void InsertMethod(BankAccount bankAccount) {
        var conn = DBManager.getInstance().getConnection();
        String str = "INSERT INTO BankAccount (contributionName, userId, bankName, pin, balance) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(str)) {
            pst.setString(1, bankAccount.contributionName);
            pst.setInt(2, bankAccount.userId);
            pst.setString(3, bankAccount.bankName.toString());
            pst.setInt(4, bankAccount.pin);
            pst.setBigDecimal(5, bankAccount.balance);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BankAccount TryEnterBankAccount(BankAccount bankAccount, int pin) {
        if (pin == bankAccount.pin) {
            return bankAccount;
        } else {
            return null;
        }
    }

    public BankAccount GetById(int id) {
        var conn = DBManager.getInstance().getConnection();
        String sql = "SELECT * FROM BankAccount WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new BankAccount(
                        rs.getInt("id"),
                        rs.getString("contributionName"),
                        rs.getBigDecimal("balance"),
                        rs.getInt("userId"),
                        rs.getInt("pin"),
                        Bank.MakeFromValue(rs.getString("bankName"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void UpdateBalance(int id, BigDecimal newBalance) {
        var conn = DBManager.getInstance().getConnection();
        String sql = "UPDATE BankAccount SET balance = ? WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setBigDecimal(1, newBalance);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
