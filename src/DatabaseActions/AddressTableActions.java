package DatabaseActions;
import Entities.Address;

import java.sql.*;

public class AddressTableActions extends  DatabaseManipulation {
    Connection con;
    Statement statement;
    Address address;

    public AddressTableActions(Address address, Connection con) {
        this.con = con;
        this.address = address;
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
            statement.executeUpdate("create table IF NOT EXISTS Address (" +
                    "Address_id integer PRIMARY KEY AUTOINCREMENT, " +
                    "line1 string, " +
                    "city string, " +
                    "state string, " +
                    "country string, " +
                    "zipCode int)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void add() {
        try {
            PreparedStatement st = con.prepareStatement("insert into Address(line1,city,state,country,zipCode) values(?,?,?,?,?)");
            st.setString(1, address.getLine1());
            st.setString(2, address.getCity());
            st.setString(3, address.getState());
            st.setString(4, address.getCountry());
            st.setString(5, address.getZipCode().toString());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(int addressID) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE address SET line1 = ?, city = ?, state =? , country =?, zipCode=?" +
                    " WHERE address_id = ?");
            st.setString(1, address.getLine1());
            st.setString(2, address.getCity());
            st.setString(3, address.getState());
            st.setString(4, address.getCountry());
            st.setString(5, address.getZipCode().toString());
            st.setInt(6, addressID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int addressID) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from address WHERE address_id = ?");
            st.setInt(1, addressID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAllItems() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM address");
            ResultSet rs = statement.executeQuery();
            String name;

            while (rs.next()) {
                // read the result set
                String line1 = rs.getString("line1");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String country = rs.getString("country");
                System.out.println("Line1: "+line1+", City: "+city+", State: "+state+", Country:"+country);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Other functions
    public Address getAddress(int addressId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM address where address_Id = ?");
            statement.setInt(1, addressId);
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

