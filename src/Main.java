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
    static Address displayAddressEntry(Scanner scn){
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
        return  new Address(line1, city, state, country, zipCode);
    }
    static Customer displayCustomerEntry(Scanner scn){
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
        return new Customer(firstName, lastName,phone, username,password, address);
    }
    public static void main(String[] args) {
        //singleton pattern in action
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        System.out.println("Welcome to Carpets Inc");
        System.out.println("Enter a new Customer");
        Scanner scn = new Scanner(System.in);
        Customer newCustomer = displayCustomerEntry(scn);
        //add a customer
        CustomerTableActions customerTableActions = new CustomerTableActions(newCustomer, con);
        System.out.println("Adding a customer....");
        int customerId = customerTableActions.add();
        if(customerId>0){
            System.out.println("Added Succesfully");
        }
        else{
            System.out.println("Error adding customer");
        }

        // display carpets
        CarpetTableActions carpetTableActions = new CarpetTableActions(null, con);
        System.out.println("Carpets available in the store");
        carpetTableActions.printAll();

        //Select the carpet
        System.out.println("\nSelect the carpet id that the customer wants to buy");
        int carpetId = Integer.parseInt(scn.nextLine());

        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId= orderTableActions.add();
        //facade pattern in use
        CarpetOrderFacade customerOrderFacade =
                new CarpetOrderFacade(new CarpetOrder(customerId,orderId,carpetId, con));
        customerOrderFacade.orderCarpet();
        System.out.println("Added to cart");
        System.out.println(customerOrderFacade);
    }
}