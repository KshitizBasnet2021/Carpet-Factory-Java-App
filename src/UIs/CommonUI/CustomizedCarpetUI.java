package UIs.CommonUI;

import DatabaseActions.CarpetTableActions;
import DatabaseActions.CustomizedOrderTableActions;
import DecoratorPattern.*;
import Entities.Carpet;
import Entities.CustomizedCarpetOrder;
import CustomizedCarpetOrders.CustomizedCarpetOrderActions;

import java.sql.Connection;
import java.util.Scanner;

public class CustomizedCarpetUI {
    Scanner scn;
    Connection con;
    int userId;

    public CustomizedCarpetUI(Scanner scn, Connection con, int userId) {
        this.scn = scn;
        this.con = con;
        this.userId = userId;
        displayCustomizedCarpetUI();
    }

    public void displayCustomizedCarpetUI() {
        try {
            System.out.println("All Carpets Available in the store");
            int carpetId = new CarpetExistanceChecker(con, scn).getValidCarpetId();
            CarpetTableActions carpetTableActionsTest = new CarpetTableActions(null, con);
            Carpet carpetToBeCustomized = carpetTableActionsTest.getCarpet(carpetId);

            //creation of customized carpet
            CustomizedCarpetOrder customizedCarpetOrder = displayCustomizeCarpetCreation(carpetToBeCustomized);

            CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
            CustomizedCarpetOrderActions customizedCarpetOrderActions = new CustomizedCarpetOrderActions(customizedOrderTableActions);
            int orderId = customizedCarpetOrderActions.add();
            if (orderId > 0) {
                System.out.println("Carpet Order Customization done!");
                System.out.println("Your customized carpet order details");
                CustomizedCarpetOrder customizedCarpetOrder1 = customizedCarpetOrderActions.get(orderId);
                System.out.println(customizedCarpetOrder1.toString());
            }
        } catch (Exception e) {
            System.out.println("Some exception occoured: " + e);
        }
    }

    private CustomizedCarpetOrder displayCustomizeCarpetCreation(Carpet carpet) {
        CustomizedCarpet customizedCarpet;
        System.out.println("Please type P to add Plastic bottom layer in the carpet or type any key word to add Fabric Material");
        String userinput = scn.nextLine();
        if (userinput.equals("P")) {
            customizedCarpet = new PlasticBottomLayer(carpet);
        } else {
            customizedCarpet = new FabricBottomLayer(carpet);
        }
        return addMaterials(customizedCarpet, carpet.getCarpetId());
    }

    private CustomizedCarpetOrder addMaterials(CustomizedCarpet customizedCarpet, int carpetId) {
        System.out.println("Please type G to add Gold material in the carpet or type any key word to add Wool Material");
        System.out.println("Remember you can add as much materials as possible");
        String userinput = scn.nextLine();
        CustomizedCarpet materialAddedCarpet;
        if (userinput.equals("G")) {
            materialAddedCarpet = new GoldMaterial(customizedCarpet);
            System.out.println("Would you like to add wool material too? Press n to cancel or any keyword for ok");
        } else {
            materialAddedCarpet = new WoolMaterial(customizedCarpet);
            System.out.println("Would you like to add gold material too? Press n to cancel or any keyword for ok");
        }
        String userInput2 = scn.nextLine();
        if (userInput2.equals("n")) {
            return new CustomizedCarpetOrder(0, "", carpetId, userId, materialAddedCarpet.getDescription(), materialAddedCarpet.getPrice());
        } else {
            CustomizedCarpet materialAddedCarpet2;
            if (userinput.equals("G")) {
                materialAddedCarpet2 = new WoolMaterial(materialAddedCarpet);
            } else {
                materialAddedCarpet2 = new GoldMaterial(materialAddedCarpet);
            }
            return new CustomizedCarpetOrder(0, "", carpetId, userId, materialAddedCarpet2.getDescription(), materialAddedCarpet2.getPrice());
        }
    }
}
