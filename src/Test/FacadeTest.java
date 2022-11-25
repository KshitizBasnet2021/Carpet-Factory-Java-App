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

import java.sql.Connection;


public class FacadeTest {
    @Test
    @DisplayName("Facade Testing")
    void FacadeTest()
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
        CarpetOrderFacade customerOrderFacade =
                new CarpetOrderFacade(new CarpetOrder(customerId,orderId,carpetId, con));
        customerOrderFacade.orderCarpet();
        Assertions.assertEquals("Carpet ID: " +carpetId+", Name: Test Carpet, Height: 10.1, Width: 8.0, Material: Fabric,Price: $10.0", customerOrderFacade.toString(),"Should be equal");
    }
}
