package Test.FacadeTest;
import CarpetSearch.CarpetSearch;
import CustomizedCarpetOrders.CustomizedCarpetOrderActions;
import DatabaseActions.*;
import DecoratorPattern.CustomizedCarpet;
import DecoratorPattern.GoldMaterial;
import DecoratorPattern.PlasticBottomLayer;
import DecoratorPattern.WoolMaterial;
import Entities.Carpet;
import Entities.Cart;
import Entities.CustomizedCarpetOrder;
import FacadePattern.*;

import Singleton.DatabaseConnection;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;


public class FacadeTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @Test
    @DisplayName("Facade Order Testing(Home Delivery)")
    void FacadeOrderTestHomeDelivery() {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        //add customer
        int customerId = 1;
        //add carpet
        Carpet carpet = new Carpet(1, "Test Carpet", 10.1, 8.0, "Fabric", 10);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        //place an order
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        Delivery delivery = new Delivery(true);
        CarpetOrder carpetOrder = new CarpetOrder(customerId, orderId, carpetId, con);
        CarpetOrderFacade customerOrderFacade =
                new CarpetOrderFacade(carpetOrder, delivery,new Packaging());
        customerOrderFacade.orderCarpet();
       customerOrderFacade.deliver();
       String cartDetails = customerOrderFacade.getAllCarts().get(0).toString();
       //Assertions
        String expectedOutput= "a string containing " +
                "\"Starting Packaging" +
                "\\nPackaging successful\\r\\n" +
                "1. "+cartDetails+
                "\\r\\nHome Delivery started" +
                "\\r\\nCustomer and Address Details\\r\\n" +
                "First Name: Customer, Last Name: customer, Phone: 98422, User Name:safas, Address: Fairsw, Chicago, Illinois, USA, 60458" +
                "\\r\\nExpected delivery date: 2-3 business days\"";
        String actualOutput = StringContains.containsString(out.toString()).toString();
        //compare output
        Assertions.assertEquals(expectedOutput, actualOutput, "Should be equal");
        //order details match
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(), "Should be equal");
    }

    @Test
    @DisplayName("Facade Order Testing(Instore purchase)")
    void FacadeOrderInStorePurchase() {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        //add customer
        int customerId = 1;
        //add carpet
        Carpet carpet = new Carpet(1, "Test Carpet", 10.1, 8.0, "Fabric", 10);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        //place an order
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        Delivery delivery = new Delivery(true);
        CarpetOrder carpetOrder = new CarpetOrder(customerId, orderId, carpetId, con);
        CarpetOrderFacade customerOrderFacade =
                new CarpetOrderFacade(carpetOrder, delivery,new Packaging());
        customerOrderFacade.orderCarpet();

        String cartDetails = customerOrderFacade.getAllCarts().get(0).toString();
        //Assertions
        String expectedOutput= "a string containing " +
                "\"Starting Packaging" +
                "\\nPackaging successful\\r\\n\"";
        String actualOutput = StringContains.containsString(out.toString()).toString();
        //compare output
        Assertions.assertEquals(expectedOutput, actualOutput, "Should be equal");
        //order details match
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(), "Should be equal");
    }



}