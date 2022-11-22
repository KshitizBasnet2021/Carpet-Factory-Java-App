package Test;
import DatabaseActions.AddressTableActions;
import Entities.Address;
import Entities.Carpet;
import Entities.Customer;
import FacadePattern.*;

import Singleton.DatabaseConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FacadeTest {
    @Test
    @DisplayName("Facade Testing")
    void FacadeTest()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Address address = new Address("1 Fairway Dr", "Chicago", "Illinois", "USA", 60458);
        AddressTableActions addressTableActions = new AddressTableActions(address, con);
        Customer customer = new Customer("Matt", "Test", 987654321, "test", "text", address );
        Carpet carpet = new Carpet("Test Carpet", 10.1, 8.0, "Fabric", 10);
        Order.OrderStatus pending = Order.OrderStatus.Completed;
        boolean homeDelivery = true;
        CarpetOrderFacade customerOrderFacade =
                new CarpetOrderFacade(new Order(customer, carpet, pending, homeDelivery), address);
        assertEquals("Matt ordered Test Carpet\n" +
                        "Completed Order\n" +
                        "Customer Asked for Home Delivery",customerOrderFacade.orderCarpet(),
                "Shot Glass should be ordered by user named Test");

    }
}
