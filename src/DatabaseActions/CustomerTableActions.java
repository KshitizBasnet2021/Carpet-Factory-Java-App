package DatabaseActions;
import Entities.Customer;

import java.sql.*;

public class CustomerTableActions extends  DatabaseManipulation {
    Connection con;
    Statement statement;
    Customer customer;

    public CustomerTableActions(Customer customer, Connection con) {
        this.con = con;
        this.customer = customer;
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
                    "phone int, " +
                    "user_name string," +
                    "password int)+" +
                    "addressId int");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void add() {
        try {
            PreparedStatement st = con.prepareStatement("insert into customer(first_name,last_name,phone,username,password, address_Id) values(?,?,?,?,?,?)");
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setInt(3, (Integer) customer.getPhone());
            st.setString(4, customer.getUserName());
            st.setString(5, customer.getPassword().toString());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(int customerID) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE customer SET" +
                    " first_name = ?," +
                    " last_name = ?," +
                    " phone = ?," +
                    " user_name = ?" +
                    " password = ?" +
                    " WHERE customer_id = ?");
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setInt(3, (Integer) customer.getPhone());
            st.setString(4, customer.getUserName());
            st.setString(5, customer.getPassword());
            st.setInt(6, customerID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int customerID) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from customer WHERE customer_id = ?");
            st.setInt(1, customerID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAllItems() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customer");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // read the result set
                String firstName = rs.getString("line1");
                String lastName = rs.getString("city");
                Number phone = rs.getInt("phone");
                String username = rs.getString("userName");
                System.out.println("First Name: "+firstName+
                        ", Last Name: "
                        +lastName+
                        ", Phone: "
                        +phone+
                        ", User Name:"+
                        username
                        );
            }

        } catch (Exception ex) {
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
            return new Customer(firstName, lastName, phone, userName,null,null);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

