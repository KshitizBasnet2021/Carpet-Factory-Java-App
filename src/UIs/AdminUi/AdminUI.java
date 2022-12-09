package UIs.AdminUi;

import UIs.CommonUI.CarpetsUI;
import UIs.CommonUI.CustomizedCarpetUI;
import UIs.CustomerRegistration.CustomerRegistrationUI;

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
            System.out.println("Enter O for carpet options or press any keyword for order");
            String userOption = scn.nextLine();
            if(userOption.equals("O")){
                CarpetsUI carpetsUI = new CarpetsUI(con,scn);
                System.out.println("Enter any key word to add, U to update a carpet and D to delete a carpet");
                String userCRUDOption = scn.nextLine();
                carpetsUI.crudCarpetUI(userCRUDOption);
            }
            else {
                System.out.println("Is customer already in the system? Press Y for yes or any keyword for no");
                String userInput = scn.nextLine();
                if (userInput.equals("Y")) {
                    CustomerFinderUI customerFinderUI = new CustomerFinderUI();
                    customerFinderUI.getCustomerfromtheSystem(con, scn);
                } else {
                    CustomerRegistrationUI addCustomerUI = new CustomerRegistrationUI(con, scn, "A");
                    addCustomerUI.enterCustomerManually(con, scn);
                }
            }
        }
    }
}