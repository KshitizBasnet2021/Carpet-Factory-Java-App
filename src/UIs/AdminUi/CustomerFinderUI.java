package UIs.AdminUi;

import DatabaseActions.CustomerTableActions;
import UIs.CommonUI.CarpetsUI;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerFinderUI {
    public CustomerFinderUI(){}
    public void getCustomerfromtheSystem(Connection con, Scanner scn){
        System.out.println("Enter the username of customer");
        String username = scn.nextLine();
        CustomerTableActions customerTableActions = new CustomerTableActions(null, con);
        int customerId = customerTableActions.getIDFromUserName(username);
        if (customerId > 0) {
            System.out.println("Customer Found");
            CarpetsUI carpetsUI = new CarpetsUI(con, scn);
            carpetsUI.carpetOrder(customerId);
        } else {
            System.out.println("Customer not found");
            System.out.println("Would you want to see the list all the customers? Press Y for yes");
            String userInput = scn.nextLine();
            if (userInput.equals("Y")) {
                customerTableActions.printAll();
            }
        }
    }
}
