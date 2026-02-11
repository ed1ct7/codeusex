package task4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class DBManager {
    private static DBManager instance;
    private Connection conn;

    private DBManager() {
        try {
            File dbfile = new File("sqlite/testishe.db");
            dbfile.getParentFile().mkdirs();
            if (!dbfile.exists()) {
                dbfile.createNewFile();
            }
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getPath());
            System.out.println("Database connected");
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    private void createTables() {
        String[] sql = {
                """
        CREATE TABLE IF NOT EXISTS User (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            surname TEXT NOT NULL,
            secondName TEXT,
            address TEXT NOT NULL,
            phoneNumber TEXT NOT NULL,
            password TEXT NOT NULL,
            seriesAndNumber TEXT NOT NULL UNIQUE
        );
        """,
                """
        CREATE TABLE IF NOT EXISTS BankAccount (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            contributionName TEXT NOT NULL DEFAULT 'Standard',
            balance REAL DEFAULT 0,
            userId INTEGER NOT NULL,
            bankName TEXT NOT NULL,
            pin INT NOT NULL,
            FOREIGN KEY (userId) REFERENCES User(id)
        )
        """
        };

        try (Statement st = conn.createStatement()) {
            for (String s : sql) {
                st.execute(s);
            }
            System.out.println("Tables created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}