package Test.CarpetSearchTest;

import CarpetSearch.CarpetSearch;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;

public class CarpetSearchTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }
    @DisplayName("Search Testing")
    @Test
    void SearchTest() {
        setStreams();
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        CarpetSearch carpetSearch = new CarpetSearch("Randommm", con);
        carpetSearch.display();
        String expectedOutput = "Sorry, could not find any carpets that starts with Randommm";
        Assertions.assertEquals((expectedOutput), out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
