package Test.IteratorTest;

import Entities.Address;
import Entities.Customer;
import Iterator.*;
import Iterator.CommonList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


public class IteratorTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @Test
    @DisplayName("ArrayList Customer Iterator Test")
    void CustomerArrayListIteratorTest() {
        setStreams();
        ArrayList<Customer> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(new Customer("Test 1","afas",54545,"afas","sdf",null));
        listOfCustomers.add(new Customer("Test 2","afas",54545,"afas","sdf",null));
        listOfCustomers.add(new Customer("Test 3","afas",54545,"afas","sdf",null));

        CommonList customerList = new CommonList(listOfCustomers);
        Iterator<Customer> customerListIterator = customerList.createIterator();


        //just generate output of customer list
        IteratorOutputs iteratorOutput1 = new IteratorOutputs(customerListIterator);
        iteratorOutput1.displayData();


        String expectedOutput = "1. First Name: Test 1, Last Name: afas, Phone: 54545, User Name:afas, Address: " +
                "2. First Name: Test 2, Last Name: afas, Phone: 54545, User Name:afas, Address: " +
                "3. First Name: Test 3, Last Name: afas, Phone: 54545, User Name:afas, Address: ";
        Assertions.assertEquals((expectedOutput) , out.toString().replaceAll("\n", "").replaceAll("\r", ""));
}
    @Test
    @DisplayName("ArrayList Address Iterator Test")
    void AddressArrayListIteratorTest() {
        setStreams();

        ArrayList<Address> listOfAddress = new ArrayList<>();

        listOfAddress.add(new Address("Address 1","afas","54545","afas",606555));
        listOfAddress.add(new Address("Address 2","afas","54545","afas",606555));
        CommonList addressList = new CommonList(listOfAddress);
        Iterator<Address> addressListIterator = addressList.createIterator();

        //just generate output of addresses
        IteratorOutputs iteratorOutputs = new IteratorOutputs(addressListIterator);
        iteratorOutputs.displayData();
        String expectedOutput = "1. Address 1, afas, 54545, afas, 606555" +
                "2. Address 2, afas, 54545, afas, 606555";
        Assertions.assertEquals((expectedOutput) , out.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
