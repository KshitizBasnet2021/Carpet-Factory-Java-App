package Test.FeatureTest;

import Features.Login.Login;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;


public class LoginTests {


    @Test
    @DisplayName("Check customer true Login")
    void customerTrueLogin() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Login loggedIn = new Login("Test_username1", "adas", "Customer", con);
        int customerId = loggedIn.getCustomerID();
        Assertions.assertEquals(12, customerId, "Should be 12");
    }

    @Test
    @DisplayName("Check customer false Login")
    void customerFalseLogin() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Login loggedIn = new Login("Test_username1", "random", "Customer", con);
        int customerId = loggedIn.getCustomerID();
        Assertions.assertEquals(0, customerId, "Should be 0");
    }

    @Test
    @DisplayName("Check admin true Login")
    void AdminTrueLogin() {
        Login loggedIn = new Login("admin", "admin", "Admin", null);
        int adminId = loggedIn.getAdminLogin();
        Assertions.assertEquals(1, adminId, "Should be 1");
    }

    @Test
    @DisplayName("Check admin false Login")
    void AdminFalseLogin() {
        Login loggedIn = new Login("admin", "random", "Admin", null);
        int adminId = loggedIn.getAdminLogin();
        Assertions.assertEquals(0, adminId, "Should be 0");
    }
}
