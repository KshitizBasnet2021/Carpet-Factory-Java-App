import Features.Login.Login;
import Singleton.DatabaseConnection;
import UIs.AdminUi.AdminUI;
import UIs.CustomerRegistration.CustomerRegistrationUI;
import UIs.CustomerUI.CustomerUI;

import java.sql.Connection;
import java.util.Scanner;

public class AuthenticationUI {
    Connection con;
    Scanner scn;
    String type;
    int loggedInUser;

    public AuthenticationUI() {
        this.con = DatabaseConnection.getInstance(false).getConnection();
        this.scn = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to ABC Carpets Inc");
            System.out.println("Enter R to register, Enter E to login as employee or any keyword to login as customer");
            String userInput = scn.nextLine();
            if (userInput.equals("E")) {
                this.type = "Admin";
            } else if (userInput.equals("R")) {
                CustomerRegistrationUI addCustomerUI = new CustomerRegistrationUI(con, scn, "");
                addCustomerUI.enterCustomerManually(con, scn);
                break;
            }
            else {
                this.type = "Customer";
            }
            this.loggedInUser = login();
            checkLogin();
        }
    }

    public void checkLogin() {
        if (this.loggedInUser == 0) {
            System.out.println("Your credentials are incorrect please try again");
        } else {
            if (type == "Admin") {
                displayAdminUi();
            } else {
                displayCustomerUI();
            }
        }
    }

    public int login() {
        System.out.println("Welcome " + type);
        System.out.println("Enter login Details");
        System.out.println("Enter username");
        String username = scn.nextLine();
        System.out.println("Enter password");
        String password = scn.nextLine();
        Login login = new Login(username, password, type, con);
        if (type.equals("Customer")) {
            return login.getCustomerID();
        } else {
            return login.getAdminLogin();
        }
    }

    public void displayAdminUi() {
        new AdminUI(con, scn).start();
    }
    public void displayCustomerUI() {
        new CustomerUI(con, scn, loggedInUser).start();
    }
}