package DatabaseActions;

import Entities.Address;

import java.sql.Connection;

public abstract class DatabaseManipulation {
    public abstract void createTableIfNotExists();
    public abstract int add();
    public abstract void update(int id);
    public abstract void delete(int id);
    public abstract void printAllItems();

}
