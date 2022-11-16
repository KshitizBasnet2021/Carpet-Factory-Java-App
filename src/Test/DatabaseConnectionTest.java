package Test;

import DatabaseConnection.DatabaseConnection;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DatabaseConnectionTest {
    Connection con = DatabaseConnection.getInstance(false).getConnection();

    // verifying that the Database connection instance is not null.
    @Test
    @DisplayName("Check Database Connection")
    void DBConnect() {
        assertNotEquals(null, con, "Should Not be Null");
    }

    // verifying that the db connection works
    @Test
    @DisplayName("Check Database Connection")
    void DBSelect() throws SQLException {
        Statement statement = con.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        statement.executeUpdate("drop table if exists person");
        statement.executeUpdate("create table person (id integer, name string)");
        statement.executeUpdate("insert into person values(1, '45454')");

        ResultSet rs = statement.executeQuery("select * from person where id = 1");
        String name;
        while (rs.next()) {
            // read the result set
            name = rs.getString("name");
            assertEquals("45454", name,
                    "Should Match");
        }
    }


}
