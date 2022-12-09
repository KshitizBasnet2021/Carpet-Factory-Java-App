package DatabaseActions;

import Entities.CustomizedCarpetOrder;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomizedOrderTableActions extends DatabaseManipulation {
    Connection con;
    Statement statement;

    CustomizedCarpetOrder customizedCarpetOrder;

    int lastCreatedOrderId;

    public CustomizedOrderTableActions(Connection con, CustomizedCarpetOrder customizedCarpetOrder) {
        this.con = con;
        this.customizedCarpetOrder = customizedCarpetOrder;
        try {
            this.statement = con.createStatement();
            createTableIfNotExists();
        } catch (SQLException sqlException) {
            System.out.println("Sql Exception");
        }
    }

    public int getLastCreatedOrderId() {
        return lastCreatedOrderId;
    }

    //create
    public void createTableIfNotExists() {
        try {
            statement.executeUpdate("create table IF NOT EXISTS customizedOrders(" +
                    "order_id integer PRIMARY KEY AUTOINCREMENT," +
                    "order_date date," +
                    "customerId int," +
                    "carpetId int," +
                    "description string," +
                    "price double" +
                    ")");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int add() {
        try {
            PreparedStatement st = con.prepareStatement("insert into customizedOrders(order_date, " +
                    "customerId," +
                    "carpetId," +
                    "description," +
                    "price) " +
                    "values(?,?,?,?,?)");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            st.setString(1, dtf.format(now));
            st.setInt(2, customizedCarpetOrder.getCustomerId());
            st.setInt(3, customizedCarpetOrder.getCarpetId());
            st.setString(4, customizedCarpetOrder.getDescription());
            st.setDouble(5, customizedCarpetOrder.getPrice());
            st.executeUpdate();
            this.lastCreatedOrderId = (int) st.getGeneratedKeys().getLong(1);
            return this.lastCreatedOrderId;
        } catch (Exception e) {
            System.out.println();
        }
        return 0;
    }

    @Override
    public void update(int orderId) {
    }

    @Override
    public void delete(int orderId) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from customizedOrders WHERE order_id = ?");
            st.setInt(1, orderId);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAll() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customizedOrders");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // read the result set
                int orderId = rs.getInt("order_id");
                String orderDate = rs.getString("order_date");
                int customerId = rs.getInt("customerId");
                int carpetId = rs.getInt("carpetId");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                System.out.println("Order ID: " + orderId + ", Order Date: " + orderDate + ", Customer Id:" + customerId + ", Carpet Id:" + carpetId + ", Description:" + description + ", Price: $" + price);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Other functions
    public CustomizedCarpetOrder getOrder(int orderId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM customizedOrders where order_Id = ?");
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            int id = rs.getInt("order_id");
            String orderDate = rs.getString("order_date");
            int customerId = rs.getInt("customerID");
            int carpetId = rs.getInt("carpetId");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            return new CustomizedCarpetOrder(id, orderDate, customerId, carpetId, description, price);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

