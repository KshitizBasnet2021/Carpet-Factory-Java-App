package DatabaseActions;

import Entities.Carpet;

import java.sql.*;

public class CarpetTableActions extends DatabaseManipulation{
    Connection con;
    Statement statement;
    Carpet carpet;
    int lastCreatedCarpetId = 0;
    public CarpetTableActions(Carpet carpet, Connection con) {
        this.con = con;
        this.carpet = carpet;
        try {
            this.statement = con.createStatement();
            createTableIfNotExists();
        } catch (SQLException sqlException) {
            System.out.println("Sql Exception");
        }
    }

    public int getLastCreatedCarpetId(){
        return lastCreatedCarpetId;
    }
    //create
    public void createTableIfNotExists() {
        try {
            statement.executeUpdate("create table IF NOT EXISTS Carpet (" +
                    "carpet_Id integer PRIMARY KEY AUTOINCREMENT, " +
                    "name string," +
                    "height double, " +
                    "width double, " +
                    "material string, " +
                    "price double)");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int add() {
        try {
            PreparedStatement st = con.prepareStatement("insert into Carpet(name,height,width,material,price) values(?,?,?,?,?)");
            st.setString(1, carpet.getName());
            st.setDouble(2, carpet.getHeight());
            st.setDouble(3, carpet.getWidth());
            st.setString(4, carpet.getMaterial());
            st.setDouble(5, carpet.getPrice());
            st.executeUpdate();
            this.lastCreatedCarpetId = (int) st.getGeneratedKeys().getLong(1);
            return lastCreatedCarpetId;
        }
        catch (Exception e) {
            System.out.println();
        }
        return 0;
    }

    @Override
    public void update(int CarpetID) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE Carpet SET name = ?, height = ?, width =? , material =?, price=?" +
                    " WHERE Carpet_id = ?");
            st.setString(1, carpet.getName());
            st.setDouble(2, carpet.getHeight());
            st.setDouble(3, carpet.getWidth());
            st.setString(4, carpet.getMaterial());
            st.setDouble(5, carpet.getPrice());
            st.setInt(6, CarpetID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int CarpetID) {
        try {
            PreparedStatement st = con.prepareStatement("Delete from Carpet WHERE Carpet_id = ?");
            st.setInt(1, CarpetID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAll() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Carpet");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // read the result set
                int carpetId = rs.getInt("carpet_Id");
                String name = rs.getString("name");
                Double height = rs.getDouble("height");
                Double width = rs.getDouble("width");
                String material = rs.getString("material");
                double price = rs.getDouble("price");
                Carpet carpet = new Carpet(carpetId,name, height, width, material, price);
                System.out.println(carpet);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Other functions
    public Carpet getCarpet(int CarpetId) {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Carpet where Carpet_Id = ?");
            statement.setInt(1, CarpetId);
            ResultSet rs = statement.executeQuery();
            String name = rs.getString("name");
            Double height = rs.getDouble("height");
            Double width = rs.getDouble("width");
            String material = rs.getString("material");
            Double price = rs.getDouble("price");
            int carpetId = rs.getInt("carpet_id");
            return new Carpet(carpetId, name, height, width, material, price);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}