package FacadePattern;

import DatabaseActions.CarpetTableActions;

import java.sql.Connection;

public class CarpetSearch {
    CarpetTableActions carpetTableActions;
    String searchString;

    public CarpetSearch(String searchString, Connection con){
        this.searchString = searchString;
        this.carpetTableActions = new CarpetTableActions(null,con);
    }

    public void display(){
        carpetTableActions.searchCarpet(searchString);
    }

}
