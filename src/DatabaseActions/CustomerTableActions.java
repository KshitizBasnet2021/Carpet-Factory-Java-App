package DatabaseActions;
import Entities.Address;
import Entities.Customer;
import Iterator.CommonList;
import Iterator.IteratorOutputs;
import Iterator.Iterator;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.ArrayList;

public class CustomerTableActions extends  DatabaseManipulation {
    Connection con;
    Statement statement;
    Customer customer;
    Address address;

    AddressTableActions addressTableActions;

    public CustomerTableActions(Customer customer, Connection con) {
        this.con = con;
        this.customer = customer;
        if(customer == null){
            this.address = null;
        }else {
            this.address = customer.getAddress();
        }

        this.addressTableActions = new AddressTableActions(address, con);
        try {
            this.statement = con.createStatement();
            createTableIfNotExists();
        } catch (SQLException sqlException) {
            System.out.println("Sql Exception");
        }
    }


    //create
    public void createTableIfNotExists() {
        try {
            statement.executeUpdate("create table IF NOT EXISTS customer (" +
                    "customer_id integer PRIMARY KEY AUTOINCREMENT, " +
                    "first_name string, " +
                    "last_name string, " +
                    "phone integer unique, " +
                    "user_name string unique," +
                    "password string, " +
                    "address_Id integer, " +
                    "FOREIGN KEY(address_Id) REFERENCES Address (Address_ID) ON UPDATE CASCADE ON DELETE SET NULL) ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int add() {
        try {
            int createdAddressId = this.addressTableActions.add();
            PreparedStatement st = con.prepareStatement("insert into customer(first_name,last_name,phone,user_name,password, address_Id) values(?,?,?,?,?,?)");
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setInt(3, (Integer) customer.getPhone());
            st.setString(4, customer.getUserName());
            st.setString(5, customer.getPassword());
            st.setInt(6, createdAddressId);
            st.executeUpdate();
            return (int) st.getGeneratedKeys().getLong(1);
        }  catch(SQLiteException e)
        {
           if(e.getLocalizedMessage().contains("customer.user_name")){
               System.out.println("This username is already present in the system");
           };
            if(e.getLocalizedMessage().contains("customer.phone")){
                System.out.println("The phone number is is already entered in the system.");
            };
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public void update(int customerID) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE customer SET" +
                    " first_name = ?," +
                    " last_name = ?," +
                    " phone = ?," +
                    " user_name = ?," +
                    " password = ?" +
                    " WHERE customer_id = ?");
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setInt(3, (Integer) customer.getPhone());
            st.setString(4, customer.getUserName());
            st.setString(5, customer.getPassword());
            st.setInt(6, customerID);
            st.executeUpdate();
            //update address if any

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void update(int customerID, int addressId){
        update(customerID);
        this.addressTableActions.update(addressId);
    }
    @Override
    public void delete(int customerID) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from customer WHERE customer_ID = ?");
            st.setInt(1,customerID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAll() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customer");
            ResultSet rs = statement.executeQuery();
            ArrayList<Customer> customerArrayList = new ArrayList<>();
            while (rs.next()) {
                // read the result set
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Number phone = rs.getInt("phone");
                String username = rs.getString("user_name");
                String address_id = rs.getString("address_id");
                Address customerAddress = addressTableActions.getAddress(Integer.parseInt(address_id));
                Customer customer = new Customer(firstName, lastName, phone, username, address_id, customerAddress);
                customerArrayList.add(customer);
            }
            //Iterator pattern in action
            CommonList customerList = new CommonList(customerArrayList);
            Iterator<Address> addressListIterator = customerList.createIterator();

            //just generate output of customers
            IteratorOutputs iteratorOutputs = new IteratorOutputs(addressListIterator);
            iteratorOutputs.displayData();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Other functions
    public Customer getCustomer(int customerId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customer where customer_Id = ?");
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Number phone = rs.getInt("phone");
            String userName = rs.getString("user_name");
            String address_id = rs.getString("address_id");
            Address customerAddress = addressTableActions.getAddress(Integer.parseInt(address_id));
            return new Customer(firstName, lastName, phone, userName,null,customerAddress);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public Customer getCustomer(String username) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customer where user_name = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Number phone = rs.getInt("phone");
            String userName = rs.getString("user_name");
            String address_id = rs.getString("address_id");
            Address customerAddress = addressTableActions.getAddress(Integer.parseInt(address_id));
            return new Customer(firstName, lastName, phone, userName,null,customerAddress);
        } catch(NumberFormatException ex){
            return  null;
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public int getIDFromUserName(String username){
        try {
            PreparedStatement statement = con.prepareStatement("SELECT customer_id FROM customer where " +
                    "user_name = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return rs.getInt("customer_id");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public int getCustomerAddressId(int customerId){
        try {
            PreparedStatement statement = con.prepareStatement("SELECT address_id FROM customer where customer_Id = ?");
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            int addressId = rs.getInt("address_id");
            return addressId;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public int customerLogin(String username, String password){
        try {
            PreparedStatement statement = con.prepareStatement("SELECT customer_id FROM customer where " +
                    "user_name = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            int customerId = rs.getInt("customer_id");
            return customerId;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public Address getCustomerAddress(int customerId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customer " +
                    "join address on address.address_Id = customer.address_id" +
                    " where customer_id = ? ");
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            String line1 = rs.getString("line1");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String country = rs.getString("country");
            int zipCode = rs.getInt("zipCode");
            return new Address(line1, city, state, country, zipCode);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

