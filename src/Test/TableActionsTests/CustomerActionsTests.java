package Test.TableActionsTests;

import DatabaseActions.CustomerTableActions;
import Entities.Address;
import Entities.Customer;
import Singleton.DatabaseConnection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerActionsTests {

    @Test
    @DisplayName("Customer Addition Test")
    void TestCustomerCreation()
    {
        String username = "Testd";
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Address address = new Address("Fairsw", "Chicago", "Illinois", "USA", 60458);
        Customer customer = new Customer("Customer", "customer", 98143122, username, "adas", address);
        CustomerTableActions customerTableActions = new CustomerTableActions(customer, con);
        assertEquals("First Name: Customer, Last Name: customer, Phone: 98143122, User Name:Testd, Address: Fairsw, Chicago, Illinois, USA, 60458",
                customer.toString(),
                "Customer should be added to database");
        System.out.println(customerTableActions.getCustomer(username));
    }
}
