package Test.FacadeTest;
import CarpetSearch.CarpetSearch;
import CustomizedCarpetOrders.CustomizedCarpetOrderActions;
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
        int customerId = 1;
        //add carpet
        Carpet carpet = new Carpet(1, "Test Carpet", 10.1, 8.0, "Fabric", 10);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        //place an order
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        Delivery delivery = new Delivery(true);
        CarpetOrderFacade customerOrderFacade =
                new CarpetOrderFacade(new CarpetOrder(customerId, orderId, carpetId, con), delivery,new Packaging());
        customerOrderFacade.orderCarpet();
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(), "Should be equal");
    }



}