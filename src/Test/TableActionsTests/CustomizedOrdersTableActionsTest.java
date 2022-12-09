package Test.TableActionsTests;

import DatabaseActions.CarpetTableActions;
import DatabaseActions.CustomizedOrderTableActions;

import DecoratorPattern.CustomizedCarpet;
import DecoratorPattern.GoldMaterial;
import DecoratorPattern.PlasticBottomLayer;
import DecoratorPattern.WoolMaterial;
import Entities.Carpet;
import Entities.CustomizedCarpetOrder;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class CustomizedOrdersTableActionsTest {

    @Test
    @DisplayName("Customized Order Addition Test")
    void TestCustomizedOrderCreation()
    {
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

        CustomizedCarpetOrder customizedCarpetOrder = new CustomizedCarpetOrder(0,"", 1,carpetId,woolenAndGoldCustomizedCarpet, woolenAndGoldCustomizedCarpetPrice);

        CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
        int orderId = customizedOrderTableActions.add();
        Assertions.assertEquals(customizedOrderTableActions.getLastCreatedOrderId(),
                orderId, "They should Match");
    }

    @Test
    @DisplayName("Customized  Carpet Get Test")
    void TestCustomizedOrderGet()
    {
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

        CustomizedCarpetOrder customizedCarpetOrder = new CustomizedCarpetOrder(0,"", 1,carpetId,woolenAndGoldCustomizedCarpet, woolenAndGoldCustomizedCarpetPrice);

        CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
        int orderId = customizedOrderTableActions.add();

        CustomizedCarpetOrder createdCarpet = customizedOrderTableActions.getOrder(orderId);
        Assertions.assertEquals(createdCarpet.toString(),
                "Customized Order ID:"+orderId+",Order Date: "+createdCarpet.getOrderDate()+",Carpet ID: 1,Customer ID: 1, Description: Test carpet Plastic Bottom Layered Carpet, Woolen, Gold, Price: 140.5", "They should Match");
    }

}
