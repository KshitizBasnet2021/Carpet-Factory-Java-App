package Test.FacadeTest;
import CarpetCRUDActions.CarpetActions;
import DatabaseActions.*;
import DecoratorPattern.CustomizedCarpet;
import DecoratorPattern.GoldMaterial;
import DecoratorPattern.PlasticBottomLayer;
import DecoratorPattern.WoolMaterial;
import Entities.Carpet;
import Entities.CustomizedCarpetOrder;
import FacadePattern.*;

import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;


public class FacadeTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @Test
    @DisplayName("Facade Order Testing")
    void FacadeOrderTest() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        //add customer
        int customerId = 15;

        //add carpet
        Carpet carpet = new Carpet(1, "Test Carpet", 10.1, 8.0, "Fabric", 10);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        //place an order
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        CarpetActionsFacade customerOrderFacade =
                new CarpetActionsFacade(new CarpetOrder(customerId, orderId, carpetId, con), null, null);
        customerOrderFacade.orderCarpet();
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(), "Should be equal");
    }

    @DisplayName("Facade Search Testing")
    @Test
    void FacadeSearchTest() {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        CarpetActionsFacade customerSearchFacade =
                new CarpetActionsFacade(null,  new CarpetSearch("Randommm", con), null);
        customerSearchFacade.searchCarpet();
        String expectedOutput = "Sorry, could not find any carpets that starts with Randommm";
        Assertions.assertEquals((expectedOutput), out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

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

        CustomizedCarpetOrder customizedCarpetOrder = new CustomizedCarpetOrder(0,"", 1,carpetId,woolenAndGoldCustomizedCarpet, woolenAndGoldCustomizedCarpetPrice);

        CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
        CustomizedCarpetOrderActions customizedCarpetOrderActions = new CustomizedCarpetOrderActions(customizedOrderTableActions);
        CarpetActionsFacade carpetActionsFacade = new CarpetActionsFacade(null, null, customizedCarpetOrderActions);
        int orderId = carpetActionsFacade.addCustomizedOrder();
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

        CustomizedCarpetOrder customizedCarpetOrder = new CustomizedCarpetOrder(0,"", 1,carpetId,woolenAndGoldCustomizedCarpet, woolenAndGoldCustomizedCarpetPrice);

        CustomizedOrderTableActions customizedOrderTableActions = new CustomizedOrderTableActions(con, customizedCarpetOrder);
        CustomizedCarpetOrderActions customizedCarpetOrderActions = new CustomizedCarpetOrderActions(customizedOrderTableActions);
        CarpetActionsFacade carpetActionsFacade = new CarpetActionsFacade(null, null, customizedCarpetOrderActions);
        int orderId = carpetActionsFacade.addCustomizedOrder();
        CustomizedCarpetOrder customizedCarpetOrder1 = carpetActionsFacade.getCustomizedOrder(orderId);
        Assertions.assertEquals("Customized Order ID:"+orderId+",Order Date: "+customizedCarpetOrder1.getOrderDate()+",Carpet ID: 1,Customer ID: 1, Description: Test carpet Plastic Bottom Layered Carpet, Woolen, Gold, Price: 140.5",
                customizedCarpetOrder1.toString(), "They should Match");
    }
}