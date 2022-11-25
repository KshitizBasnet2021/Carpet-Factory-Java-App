package DatabaseActions;
import Entities.*;

import java.sql.*;
import java.util.ArrayList;


public class CartTableActions extends  DatabaseManipulation {
    Connection con;
    Statement statement;
    int lastCreatedCartId;

    int customerId;
    int orderId;
    int carpetId;

    public CartTableActions(Connection con) {
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
            statement.executeUpdate("create table IF NOT EXISTS Cart (" +
                    "Cart_id integer PRIMARY KEY AUTOINCREMENT" +
                    "customer_id int,"+
                    "carpet_id int," + "order_id int"+
                    ")");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int add() {
        try {
            PreparedStatement st = con.prepareStatement("insert into Cart(customer_id, carpet_id, order_id) values(?,?,?)");
            st.setInt(1,customerId );
            st.setInt(2, carpetId);
            st.setInt(3, orderId);
            this.lastCreatedCartId = (int) st.getGeneratedKeys().getLong(1);
            return this.lastCreatedCartId;
        }
        catch (Exception e) {
            System.out.println();
        }
        return 0;
    }

    @Override
    public void update(int CartId) {}

    @Override
    public void delete(int CartId) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from Cart WHERE Cart_id = ?");
            st.setInt(1, CartId);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAll() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Cart");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // read the result set
                String cartId = rs.getString("Cart_id");
                int orderId = rs.getInt("order_id");
                int customerId = rs.getInt("customer_id");
                int carpetId = rs.getInt("carpet_id");
                System.out.println("Cart ID: "+cartId+", Order Id: "+orderId+", Customer Id: "+ customerId +", Carpet Id"+carpetId);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Other functions
    public Cart getCart(int cartId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Cart where Cart_Id = ?");
            statement.setInt(1, cartId);
            ResultSet rs = statement.executeQuery();
            int orderId = rs.getInt("order_id");
            int customerId = rs.getInt("customer_id");
            int carpetId = rs.getInt("carpet_id");
            return new Cart(cartId, orderId, customerId, carpetId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Cart> getCartsFromOrderId(int orderId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Cart where order_id = ?");
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            ArrayList<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                int cartId = rs.getInt("Cart_id");
                int customerId = rs.getInt("customer_id");
                int carpetId = rs.getInt("carpet_id");
                carts.add(new Cart(cartId, orderId, customerId, carpetId));
            }
            return carts;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

