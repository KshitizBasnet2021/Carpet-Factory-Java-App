package UIs.CommonUI;

import DatabaseActions.CarpetTableActions;
import DatabaseActions.OrderTableActions;
import Entities.Carpet;
import CarpetCRUDActions.CarpetActions;
import FacadePattern.CarpetOrderFacade;
import FacadePattern.CarpetOrder;
import CarpetSearch.CarpetSearch;
import FacadePattern.Delivery;
import FacadePattern.Packaging;

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

    public void searchCarpetByName() {
        System.out.println("Please enter the carpet name. You can type what the name starts with");
        String carpetName = scn.nextLine();
        CarpetSearch carpetSearch = new CarpetSearch(carpetName, con);
        carpetSearch.display();
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
                customerCarpetOrder(customerId);
            }
        }
    }

    public void crudCarpetUI(String type) {
        int carpetId;
        if (type.equals("D")) {
            System.out.println("Enter carpet Id to delete");
            carpetId = Integer.parseInt(scn.nextLine());
            CarpetActions carpetActions = new CarpetActions(null, con);
            carpetActions.delete(carpetId);
        } else {
            if (type.equals("U")) {
                System.out.println("Enter carpet Id of the carpet to update");
                carpetId = Integer.parseInt(scn.nextLine());
            } else {
                carpetId = 0;
            }
            System.out.println("Enter the name of the carpet");
            String name = scn.nextLine();
            System.out.println("Enter the height of the carpet");
            double height = Double.parseDouble(scn.nextLine());
            System.out.println("Enter the width of the carpet");
            double width = Double.parseDouble(scn.nextLine());
            System.out.println("Enter the material of the carpet");
            String material = scn.nextLine();
            System.out.println("Enter the price of the carpet");
            double price = Double.parseDouble(scn.nextLine());

            CarpetActions carpetActions = new CarpetActions(new Carpet(carpetId, name, height, width, material, price), con);
            if (carpetId != 0) {
                carpetActions.update(carpetId);
            } else {
                carpetActions.add();
            }
        }
    }

    public void customerCarpetOrder(int customerId) {
        //Select the carpet
        System.out.println("\nEnter the carpet id that the you want to order");
        int carpetId = Integer.parseInt(scn.nextLine());

        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        while (true) {
            if (carpetId == 0) {
                System.out.println("\nSelect the carpet id that the you want to order");
                carpetId = Integer.parseInt(scn.nextLine());
            }
            System.out.println("Type H for home Delivery");
                //facade pattern in use
            CarpetOrderFacade customerOrderFacade =
                    new CarpetOrderFacade( new CarpetOrder(customerId, orderId, carpetId, con), new Delivery(false), new Packaging());
            customerOrderFacade.orderCarpet();
            System.out.println("Added to cart");
            System.out.println(customerOrderFacade);
            System.out.println("Do you want to add more carpets? Press Y to add more carpets or press any key word to no");
            String userChoice = scn.nextLine();
            if (!userChoice.equals("Y")) {
                System.out.println("Type H for home Delivery");
                String deliveryInput = scn.nextLine();
                boolean homedelivery = false;
                if(deliveryInput.equals("H")){
                    homedelivery = true;
                    customerOrderFacade.changetoHomeDelivery();
                }
                System.out.println("The items that in this order are:");
                customerOrderFacade.deliver();
                break;
            }
            carpetId = 0;
        }
    }
}
