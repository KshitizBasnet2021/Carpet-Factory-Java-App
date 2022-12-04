package UIs.AdminUi;

import DatabaseActions.CustomerTableActions;
import Entities.Address;
import Entities.Customer;
import UIs.CommonUI.CarpetsUI;

import java.sql.Connection;
import java.util.Scanner;

public class AddCustomerUI {
    Connection con;
    Scanner scn;

    public AddCustomerUI(Connection con, Scanner scn) {
        this.con = con;
        this.scn = scn;
    }
    static void enterCustomerManually(Connection con, Scanner scn){
        Customer newCustomer = displayCustomerEntry(scn);
        //add a customer
        CustomerTableActions customerTableActions = new CustomerTableActions(newCustomer, con);
        System.out.println("Adding a customer....");
        int customerId = customerTableActions.add();
        if (customerId > 0) {
            System.out.println("Added Succesfully");
            //then go to carpet order
            CarpetsUI carpetsUI = new CarpetsUI(con, scn);
            carpetsUI.carpetOrder(customerId);
        } else {
            System.out.println("Error adding customer");
        }
    }
    static Customer displayCustomerEntry(Scanner scn) {
        System.out.println("Enter the details of the customer");
        System.out.println("Enter the first name of the customer");
        String firstName = scn.nextLine();
        System.out.println("Enter the last name of the customer");
        String lastName = scn.nextLine();
        System.out.println("Enter the phone of the customer");
        int phone = Integer.parseInt(scn.nextLine());
        System.out.println("Enter the username of the customer");
        String username = scn.nextLine();
        System.out.println("Enter the password of the customer");
        String password = scn.nextLine();
        Address address = displayAddressEntry(scn);
        return new Customer(firstName, lastName, phone, username, password, address);
    }
    static Address displayAddressEntry(Scanner scn) {
        System.out.println("Enter line");
        String line1 = scn.nextLine();
        System.out.println("Enter city");
        String city = scn.nextLine();
        System.out.println("Enter state");
        String state = scn.nextLine();
        System.out.println("Enter country");
        String country = scn.nextLine();
        System.out.println("Enter zip code");
        int zipCode = Integer.parseInt(scn.nextLine());
        return new Address(line1, city, state, country, zipCode);
    }
}
