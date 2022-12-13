package Test.CustomizedCarpetOrderTest;

import CustomizedCarpetOrders.CustomizedCarpetOrderActions;
import DatabaseActions.CarpetTableActions;
import DatabaseActions.CustomizedOrderTableActions;
import DecoratorPattern.CustomizedCarpet;
import DecoratorPattern.GoldMaterial;
import DecoratorPattern.PlasticBottomLayer;
import DecoratorPattern.WoolMaterial;
import Entities.Carpet;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class CustomizedCarpetOrder {

    //Customized Carpet
    @Test
    @DisplayName("Customized Carpet")
    void TestCustomizedCarpetAdd() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        int carpetId = 1;
        CarpetTableActions carpetTableActionsTest = new CarpetTableActions(null, con);
        Carpet carpetToBeCustomized = carpetTableActionsTest.getCarpet(1);
        //create a customized carpet
        CustomizedCarpet customizedCarpet = new PlasticBottomLayer(carpetToBeCustomized);
        CustomizedCarpet woolMaterial = new WoolMaterial(customizedCarpet);
        CustomizedCarpet goldMaterial = new GoldMaterial(woolMaterial);
        String woolenAndGoldCustomizedCarpet = goldMaterial.getDescription();
        double woolenAndGoldCustomizedCarpetPrice = goldMaterial.getPrice();

        Entities.CustomizedCarpetOrder customizedCarpetOrder = new Entities.CustomizedCarpetOrder(0,"", 1,carpetId,woolenAndGoldCustomizedCarpet, woolenAndGoldCustomizedCarpetPrice);

        CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
        CustomizedCarpetOrderActions customizedCarpetOrderActions = new CustomizedCarpetOrderActions(customizedOrderTableActions);
        int orderId = customizedCarpetOrderActions.add();
        Assertions.assertEquals(customizedOrderTableActions.getLastCreatedOrderId(),
                orderId, "They should Match");
    }

    @Test
    @DisplayName("Get Customized Carpet")
    void TestCustomizedCarpetGet() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        int carpetId = 1;
        CarpetTableActions carpetTableActionsTest = new CarpetTableActions(null, con);
        Carpet carpetToBeCustomized = carpetTableActionsTest.getCarpet(1);
        //create a customized carpet
        CustomizedCarpet customizedCarpet = new PlasticBottomLayer(carpetToBeCustomized);
        CustomizedCarpet woolMaterial = new WoolMaterial(customizedCarpet);
        CustomizedCarpet goldMaterial = new GoldMaterial(woolMaterial);
        String woolenAndGoldCustomizedCarpet = goldMaterial.getDescription();
        double woolenAndGoldCustomizedCarpetPrice = goldMaterial.getPrice();

        Entities.CustomizedCarpetOrder customizedCarpetOrder = new Entities.CustomizedCarpetOrder(0,"", 1,carpetId,woolenAndGoldCustomizedCarpet, woolenAndGoldCustomizedCarpetPrice);

        CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
        CustomizedCarpetOrderActions customizedCarpetOrderActions = new CustomizedCarpetOrderActions(customizedOrderTableActions);
        int orderId = customizedCarpetOrderActions.add();
        Entities.CustomizedCarpetOrder customizedCarpetOrder1 = customizedCarpetOrderActions.get(orderId);
        Assertions.assertEquals("Customized Order ID:"+orderId+",Order Date: "+customizedCarpetOrder1.getOrderDate()+",Carpet ID: 1,Customer ID: 1, Description: Test carpet Plastic Bottom Layered Carpet, Woolen, Gold, Price: 140.5",
                customizedCarpetOrder1.toString(), "They should Match");
    }
}
