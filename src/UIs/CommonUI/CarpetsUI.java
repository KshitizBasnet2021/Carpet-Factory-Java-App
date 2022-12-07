package UIs.CommonUI;

import DatabaseActions.CarpetTableActions;
import DatabaseActions.OrderTableActions;
import FacadePattern.CarpetActionsFacade;
import FacadePattern.CarpetOrder;
import FacadePattern.CarpetSearch;

import java.sql.Connection;
import java.util.Scanner;

public class CarpetsUI {
    Connection con;
    Scanner scn;

    public CarpetsUI(Connection con, Scanner scn) {
        this.con = con;
        this.scn = scn;
    }

    public void displayAllCarpets() {
        CarpetTableActions carpetTableActions = new CarpetTableActions(null, con);
        System.out.println("Carpets available in the store");
        carpetTableActions.printAll();
    }

    public void searchCarpets(String searchString) {
        CarpetActionsFacade customerOrderFacade =
                new CarpetActionsFacade(null, new CarpetSearch(searchString, null));
        customerOrderFacade.orderCarpet();
    }

    public void searchCarpetByName(){
        System.out.println("Please enter the carpet name. You can type what the name starts with");
        String carpetName = scn.nextLine();
        CarpetActionsFacade customerOrderFacade =
                new CarpetActionsFacade(null, new CarpetSearch(carpetName,con));
        customerOrderFacade.searchCarpet();
    }
    public void carpetOrder(int customerId) {
        //display carpets
        displayAllCarpets();
        while (true) {
            System.out.println("Would you like to search carpet?Press Y for search");
            String userMood = scn.nextLine();
            if (userMood.equals("Y")) {
                searchCarpetByName();
            } else {
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
                    CarpetActionsFacade customerOrderFacade =
                            new CarpetActionsFacade(new CarpetOrder(customerId, orderId, carpetId, con), null);
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
        }
    }
}
