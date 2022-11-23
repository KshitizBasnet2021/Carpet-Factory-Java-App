package Test.TableActionsTests;

import DatabaseActions.CarpetTableActions;
import DatabaseActions.CustomerTableActions;
import Entities.Address;
import Entities.Carpet;
import Entities.Customer;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarpetTableActionsTest {

    @Test
    @DisplayName("Carpet Addition Test")
    void TestCustomerCreation()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Carpet carpet = new Carpet(0, "Test carpet", 10.1, 20.1, "Fabric", 20.0);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        Assertions.assertEquals("Name: Test carpet, Height: 10.1, Width: 20.1, Material: Fabric,Price: $20.0",
                carpetTableActions.getCarpet(carpetId).toString());
    }

    @Test
    @DisplayName("Carpet Deletion Test")
    void TestCustomerDeletion()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Carpet carpet = new Carpet(0, "Test carpet", 10.1, 20.1, "Fabric", 20.0);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        carpetTableActions.delete(carpetId);
        Carpet carpet1 = carpetTableActions.getCarpet(carpetId);
        Assertions.assertEquals("Name: null, Height: 0.0, Width: 0.0, Material: null,Price: $0.0",carpet1.toString());
    }

    @Test
    @DisplayName("Carpet update Test")
    void TestCustomerUpdate()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Carpet carpet = new Carpet(0, "Test carpet", 10.1, 20.1, "Fabric", 20.0);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
       //create first
        int carpetId = carpetTableActions.add();
        Carpet carpet1 = carpetTableActions.getCarpet(carpetId);
        Assertions.assertEquals("Name: Test carpet, Height: 10.1, Width: 20.1, Material: Fabric,Price: $20.0",carpet1.toString());
        //then update
        Carpet updatedcarpet = new Carpet(0, "Test carpet2", 10.1, 20.1, "Fabricz", 20.0);
        CarpetTableActions carpetTableAction = new CarpetTableActions(carpet, con);
        carpetTableAction.update(carpetId);
        Assertions.assertEquals("Name: Test carpet2, Height: 10.1, Width: 20.1, Material: Fabricz,Price: $20.0",updatedcarpet.toString());
    }
}
