package UIs.CustomerUI;

import UIs.CommonUI.CarpetsUI;
import UIs.CommonUI.CustomizedCarpetUI;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerUI {
    Connection con;
    Scanner scn;
    int customerId;
    public CustomerUI(Connection con, Scanner scan, int customerId) {
        this.con = con;
        this.scn = scan;
        this.customerId = customerId;
    }
    public void start(){
        while(true) {
                System.out.println("Enter C to order a customized carpet or enter any keyword to order a customized carpet");
                String userInput = scn.nextLine();
                CarpetsUI carpetsUI = new CarpetsUI(con, scn);
                System.out.println("All carpets in the store:");
            carpetsUI.displayAllCarpets();
                if(userInput.equals("C")) {
                    new CustomizedCarpetUI(scn, con,customerId);
                }
                else {
                    carpetsUI.carpetOrder(customerId);
                }

        }
    }
}