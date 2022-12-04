package DatabaseActions;

public abstract class DatabaseManipulation {
    public abstract void createTableIfNotExists();
    public abstract int add();
    public abstract void update(int id);
    public abstract void delete(int id);
    public abstract void printAll();

}
