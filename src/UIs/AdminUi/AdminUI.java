package UIs.AdminUi;

import Singleton.DatabaseConnection;

import java.sql.Connection;
import java.util.Scanner;

public class AdminUI {
    Connection con;
    Scanner scn;
    public AdminUI(Connection con, Scanner scan) {
        this.con = con;
        this.scn = scan;
    }
    public void start(){
        while(true) {
            System.out.println("Is customer already in the system? Press Y for yes or any keyword for no");
            String userInput = scn.nextLine();
            if (userInput.equals("Y")) {
                CustomerFinderUI customerFinderUI = new CustomerFinderUI();
                customerFinderUI.getCustomerfromtheSystem(con, scn);
            } else {
                AddCustomerUI addCustomerUI = new AddCustomerUI(con, scn);
                addCustomerUI.enterCustomerManually(con, scn);
            }
        }
    }
}