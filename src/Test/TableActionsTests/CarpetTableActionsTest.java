package Test.TableActionsTests;

import DatabaseActions.CarpetTableActions;
import Entities.Carpet;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;

public class CarpetTableActionsTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }
    @Test
    @DisplayName("Carpet Addition Test")
    void TestCarpetCreation() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Carpet carpet = new Carpet(0, "Test carpet", 10.1, 20.1, "Fabric", 20.0);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        Assertions.assertEquals("Carpet ID: " + carpetId + ", Name: Test carpet, Height: 10.1, Width: 20.1, Material: Fabric,Price: $20.0",
                carpetTableActions.getCarpet(carpetId).toString());
    }


    @Test
    @DisplayName("Carpet Deletion Test")
    void TestCarpetDeletion() {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Carpet carpet = new Carpet(0, "Test carpet", 10.1, 20.1, "Fabric", 20.0);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
        int carpetId = carpetTableActions.add();
        carpetTableActions.delete(carpetId);
        Carpet carpet1 = carpetTableActions.getCarpet(carpetId);
        Assertions.assertEquals("Carpet ID: 0, Name: null, Height: 0.0, Width: 0.0, Material: null,Price: $0.0", carpet1.toString());
    }

    @Test
    @DisplayName("Carpet Update Test")
    void TestCarpetrUpdate()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        Carpet carpet = new Carpet(0, "Test carpet", 10.1, 20.1, "Fabric", 20.0);
        CarpetTableActions carpetTableActions = new CarpetTableActions(carpet, con);
       //create first
        int carpetId = carpetTableActions.add();
        Carpet carpet1 = carpetTableActions.getCarpet(carpetId);
        Assertions.assertEquals("Carpet ID: "+ carpetId+", Name: Test carpet, Height: 10.1, Width: 20.1, Material: Fabric,Price: $20.0",carpet1.toString());
        //then update
        Carpet updatedcarpet = new Carpet(carpetId, "Test carpet2", 10.1, 20.1, "Fabricz", 20.0);
        CarpetTableActions carpetTableAction = new CarpetTableActions(carpet, con);
        carpetTableAction.update(carpetId);
        Assertions.assertEquals("Carpet ID: "+ carpetId+", Name: Test carpet2, Height: 10.1, Width: 20.1, Material: Fabricz,Price: $20.0",updatedcarpet.toString());
    }

    @Test
    @DisplayName("Carpet Search Test")
    void TestCarpetSearch()
    {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        CarpetTableActions carpetTableActions = new CarpetTableActions(null, con);
        carpetTableActions.searchCarpet("Randommm");
        String expectedOutput = "Sorry, could not find any carpets thet starts with Randommm";
        Assertions.assertEquals((expectedOutput) , out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
