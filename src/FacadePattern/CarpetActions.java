package FacadePattern;

import DatabaseActions.CarpetTableActions;
import Entities.Carpet;

import java.sql.Connection;

public class CarpetActions {
    CarpetTableActions carpetTableActions;

    public CarpetActions(Carpet carpet, Connection con) {
        this.carpetTableActions = new CarpetTableActions(carpet, con);
    }

    public int add() {
       int carpetId = carpetTableActions.add();
       System.out.println(carpetId);
       return carpetId;
    }

    public void update(int carpetId) {
        carpetTableActions.update(carpetId);
    }

    public void delete(int carpetId) {
        carpetTableActions.delete(carpetId);
    }
}
