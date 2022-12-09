package Test.FacadeTest;
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
                new CarpetActionsFacade(null, new CarpetOrder(customerId, orderId, carpetId, con), null, null);
        customerOrderFacade.orderCarpet();
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(), "Should be equal");
    }

    @DisplayName("Facade Search Testing")
    @Test
    void FacadeSearchTest() {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        CarpetActionsFacade customerSearchFacade =
                new CarpetActionsFacade(null, null, new CarpetSearch("Randommm", con), null);
        customerSearchFacade.searchCarpet();
        String expectedOutput = "Sorry, could not find any carpets that starts with Randommm";
        Assertions.assertEquals((expectedOutput), out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @DisplayName("Facade Carpet Addition Testing")
    @Test
    void FacadeCarpetAddTest() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        CarpetActionsFacade carpetActionsFacade =
                new CarpetActionsFacade(new CarpetActions(new Carpet(0, "Persian Carpet", 2.0, 2.0, "Fabric", 2.0), con), null, null, null);
        int carpetId = carpetActionsFacade.addCarpet();
        CarpetTableActions cta = new CarpetTableActions(null, con);
        Carpet getCarpet = cta.getCarpet(carpetId);
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Persian Carpet, Height: 2.0, Width: 2.0, Material: Fabric,Price: $2.0",
                getCarpet.toString());
    }

    @DisplayName("Facade Carpet Delete Testing")
    @Test
    void FacadeCarpetDeleteTest() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        //first add
        CarpetActionsFacade carpetActionsFacade =
                new CarpetActionsFacade(new CarpetActions(new Carpet(0, "Persian Carpet 2", 2.0, 2.0, "Fabric", 2.0), con), null, null, null);
        int createdCarpet1 = carpetActionsFacade.addCarpet();
        //then delete
        carpetActionsFacade.delete(createdCarpet1);
        CarpetTableActions cta = new CarpetTableActions(null, con);
        Carpet carpet1 = cta.getCarpet(createdCarpet1);
        Assertions.assertEquals("Carpet ID: 0, Name: null, Height: 0.0, Width: 0.0, Material: null,Price: $0.0", carpet1.toString());
    }

    @Test
    @DisplayName("Facade Carpet Update Test")
    void TestCarpetUpdate() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        //first add
        CarpetActionsFacade carpetActionsFacade =
                new CarpetActionsFacade(new CarpetActions(new Carpet(0, "Persian Carpet 2", 2.0, 2.0, "Fabric", 2.0), con), null, null, null);
        int createdCarpet1 = carpetActionsFacade.addCarpet();
        //then update
        CarpetActionsFacade carpetActionsFacade1 =
                new CarpetActionsFacade(new CarpetActions(new Carpet(createdCarpet1, "Persian Carpet 3", 2.0, 2.0, "Fabric", 2.0), con), null, null,null);
        carpetActionsFacade1.update(createdCarpet1);
        CarpetTableActions cta = new CarpetTableActions(null, con);
        Carpet editedCarpet = cta.getCarpet(createdCarpet1);
        Assertions.assertEquals("Carpet ID: "+ createdCarpet1+", Name: Persian Carpet 3, Height: 2.0, Width: 2.0, Material: Fabric,Price: $2.0", editedCarpet.toString());
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
        CarpetActionsFacade carpetActionsFacade = new CarpetActionsFacade(null, null, null,customizedCarpetOrderActions);
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
        CarpetActionsFacade carpetActionsFacade = new CarpetActionsFacade(null, null, null,customizedCarpetOrderActions);
        int orderId = carpetActionsFacade.addCustomizedOrder();
        CustomizedCarpetOrder customizedCarpetOrder1 = carpetActionsFacade.getCustomizedOrder(orderId);
        Assertions.assertEquals("Customized Order ID:"+orderId+",Order Date: "+customizedCarpetOrder1.getOrderDate()+",Carpet ID: 1,Customer ID: 1, Description: Test carpet Plastic Bottom Layered Carpet, Woolen, Gold, Price: 140.5",
                customizedCarpetOrder1.toString(), "They should Match");
    }
}