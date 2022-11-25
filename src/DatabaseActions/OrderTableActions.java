package DatabaseActions;
import Entities.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTableActions extends  DatabaseManipulation {
    Connection con;
    Statement statement;

    int lastCreatedOrderId;

    public OrderTableActions( Connection con) {
        this.con = con;
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
            statement.executeUpdate("create table IF NOT EXISTS Order (" +
                    "order_id integer PRIMARY KEY AUTOINCREMENT" +
                    "order_date date"+
                  ")");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int add() {
        try {
            PreparedStatement st = con.prepareStatement("insert into Order(order_date) values(?)");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            st.setString(1,  dtf.format(now));
            st.executeUpdate();
            this.lastCreatedOrderId = (int) st.getGeneratedKeys().getLong(1);
            return this.lastCreatedOrderId;
        }
        catch (Exception e) {
            System.out.println();
        }
        return 0;
    }

    @Override
    public void update(int orderId) {}

    @Override
    public void delete(int orderId) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from order WHERE order_id = ?");
            st.setInt(1, orderId);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAll() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM order");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // read the result set
                String orderId = rs.getString("order_id");
                String orderDate = rs.getString("order_date");
                System.out.println("Order ID: "+orderId+", Order Date: "+orderDate);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Other functions
    public Order getOrder(int OrderId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM order where order_Id = ?");
            statement.setInt(1, OrderId);
            ResultSet rs = statement.executeQuery();
            int orderId = rs.getInt("order_id");
            String orderDate = rs.getString("orderDate");

            return new Order(orderId, orderDate);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

