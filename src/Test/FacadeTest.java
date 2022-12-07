package Test;
import DatabaseActions.*;
import Entities.Address;
import Entities.Carpet;
import Entities.Customer;
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
    void FacadeOrderTest()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        //add customer
        int customerId = 15;

        //add carpet
        Carpet carpet = new Carpet(1, "Test Carpet", 10.1, 8.0, "Fabric", 10);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        //place an order
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId= orderTableActions.add();
        CarpetActionsFacade customerOrderFacade =
                new CarpetActionsFacade(new CarpetOrder(customerId,orderId,carpetId, con), null);
        customerOrderFacade.orderCarpet();
        Assertions.assertEquals("Carpet ID: " +carpetId+", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(),"Should be equal");
    }
    @DisplayName("Facade Search Testing")
    @Test
    void FacadeSearchTest()
    {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        CarpetActionsFacade customerSearchFacade =
                new CarpetActionsFacade(null, new CarpetSearch("a", con));
        customerSearchFacade.searchCarpet();
        String expectedOutput = "Sorry, could not find any carpets thet starts with a";
        Assertions.assertEquals((expectedOutput) , out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
