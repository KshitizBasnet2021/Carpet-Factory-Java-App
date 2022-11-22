package Test.TableActionsTests;

import DatabaseActions.CustomerTableActions;
import Entities.Address;
import Entities.Customer;
import Singleton.DatabaseConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerActionsTests {


    @Test
    @DisplayName("Customer Addition Test")
    void TestCustomerCreation()
    {
        String username = "Test_username2";
        int phone = 155;
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Address address = new Address("Fairsw", "Chicago", "Illinois", "USA", 60458);
        Customer customer = new Customer("Customer", "customer", phone, username, "adas", address);
        CustomerTableActions customerTableActions = new CustomerTableActions(customer, con);
        //Delete a customer if there is an existing customer with same username
        if(customerTableActions.getCustomer(username) != null){
            int customerID = customerTableActions.getIDFromUserName(username);
            customerTableActions.delete(customerID);
        }
        customerTableActions.add();
        assertEquals("First Name: Customer, Last Name: customer, Phone: "+phone+", User Name:"+ username+", Address: Fairsw, Chicago, Illinois, USA, 60458",
                customer.toString(),
                "The customer should be created");
    }
    @Test
    @DisplayName("Customer Deletion Test")
    void TestCustomerDeletion()
    {
        String username = "Test_username2";
        //create a new customer
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Address address = new Address("Fairsw", "Chicago", "Illinois", "USA", 60458);
        Customer customer = new Customer("Customer", "customer", 1234, username, "adas", address);
        CustomerTableActions customerTableActions = new CustomerTableActions(customer, con);
        int customerID = customerTableActions.getIDFromUserName(username);
        //delete customer
        customerTableActions.delete(customerID);
        //check the customerID
        int customerID2 = customerTableActions.getIDFromUserName(username);
        Assertions.assertEquals(customerID2,0);
    }

    @Test
    @DisplayName("Customer Update Test")
    void TestCustomerUpdate()
    {
        String username = "Test_username2";
        //create a new customer
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Address address = new Address("Fairsw", "Chicago", "Illinois", "USA", 60450);
        Customer customer = new Customer("Customer2", "customer", 1234, username, "adas", address);
        CustomerTableActions customerTableActions = new CustomerTableActions(customer, con);
        int customerID = customerTableActions.getIDFromUserName(username);
        int addressId = customerTableActions.getCustomerAddressId(customerID);
        //update customer with address
        customerTableActions.update(customerID, addressId);
        Assertions.assertEquals("First Name: Customer2, Last Name: customer, Phone: 1234, User Name:Test_username2, Address: Fairsw, Chicago, Illinois, USA, 60450",
                customerTableActions.getCustomer(customerID).toString());
    }
}
