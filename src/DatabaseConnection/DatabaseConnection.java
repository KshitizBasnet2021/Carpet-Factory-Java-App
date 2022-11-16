package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private final String mainDbURL = "jdbc:sqlite:Database/FinalProjectDB.db";
    private final String testDbURL = "jdbc:sqlite:Database/FinalProjectDB_Test.db";

    private DatabaseConnection(boolean type) {
        try {
            if (type) {
                this.connection = DriverManager.getConnection(mainDbURL);
            } else {
                this.connection = DriverManager.getConnection(testDbURL);
            }
        } catch (Exception e) {
            System.out.println("Database Connection Failed : \n" + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance(boolean type) {
        if (instance == null) {
            instance = new DatabaseConnection(type);
        }
        return instance;
    }
}