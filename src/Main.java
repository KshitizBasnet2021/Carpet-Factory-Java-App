import DatabaseActions.CarpetTableActions;
import DatabaseActions.CustomerTableActions;
import DatabaseActions.OrderTableActions;
import Entities.Address;
import Entities.Customer;
import FacadePattern.CarpetOrder;
import FacadePattern.CarpetOrderFacade;
import Singleton.DatabaseConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
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

    static void carpetOrder(Scanner scn, Connection con, int customerId) {
        // display carpets
        CarpetTableActions carpetTableActions = new CarpetTableActions(null, con);
        System.out.println("Carpets available in the store");
        carpetTableActions.printAll();

        //Select the carpet
        System.out.println("\nEnter the carpet id that the customer wants to buy");
        int carpetId = Integer.parseInt(scn.nextLine());

        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        while (true) {
            if (carpetId == 0) {
                System.out.println("\nSelect the carpet id that the customer wants to buy");
                carpetId = Integer.parseInt(scn.nextLine());
            }
            //facade pattern in use
            CarpetOrderFacade customerOrderFacade =
                    new CarpetOrderFacade(new CarpetOrder(customerId, orderId, carpetId, con));
            customerOrderFacade.orderCarpet();
            System.out.println("Added to cart");
            System.out.println(customerOrderFacade);
            System.out.println("Do you want to add more carpets? Press Y to add more carpets or press any key word to no");
            String userChoice = scn.nextLine();
            if (!userChoice.equals("Y")) {
                System.out.println("The items that the customer bought are:");
                customerOrderFacade.displayCarpetsinOrder();
                break;
            }
            carpetId = 0;
        }
    }
    static void getCustomerfromtheSystem( Connection con, Scanner scn){
        System.out.println("Enter the username of customer");
        String username = scn.nextLine();
        CustomerTableActions customerTableActions = new CustomerTableActions(null, con);
        int customerId = customerTableActions.getIDFromUserName(username);
        if (customerId > 0) {
            System.out.println("Customer Found");
            carpetOrder(scn, con, customerId);
        } else {
            System.out.println("Customer not found");
            System.out.println("Would you want to see the list all the customers? Press Y for yes");
            String userInput = scn.nextLine();
            if (userInput.equals("Y")) {
                customerTableActions.printAll();
            }
        }
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
            carpetOrder(scn, con, customerId);
        } else {
            System.out.println("Error adding customer");
        }
    }
    public static void main(String[] args) {
        //singleton pattern in action
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        while(true) {
            System.out.println("Welcome to Carpets Inc");
            Scanner scn = new Scanner(System.in);
            System.out.println("Is customer already in the system? Press Y for yes or any keyword for no");
            String userInput = scn.nextLine();
            int customerId;
            if (userInput.equals("Y")) {
                getCustomerfromtheSystem(con, scn);
            } else {
                enterCustomerManually(con, scn);
            }
        }
    }
}