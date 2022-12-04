package UIs.CommonUI;

import DatabaseActions.CarpetTableActions;
import DatabaseActions.OrderTableActions;
import FacadePattern.CarpetOrder;
import FacadePattern.CarpetOrderFacade;

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

    public void carpetOrder(int customerId) {
        // display carpets
        displayAllCarpets();

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
}
