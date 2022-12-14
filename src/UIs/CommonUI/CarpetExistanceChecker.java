package UIs.CommonUI;

import DatabaseActions.CarpetTableActions;
import Entities.Carpet;

import java.sql.Connection;
import java.util.Scanner;

public class CarpetExistanceChecker {
    Connection con;
    Scanner scn;
    public CarpetExistanceChecker(Connection con, Scanner scn){
        this.con = con;
        this.scn = scn;
    }
    public  int getValidCarpetId() {
        boolean checker = true;
        while (checker) {
            System.out.println("\nEnter the carpet id that the you want to order");
            int carpetId = Integer.parseInt(scn.nextLine());
            Carpet checkID = new CarpetTableActions(null, con).getCarpet(carpetId);
            if (checkID.getName() != null) {
                System.out.println("Carpet Successfully found");

                return carpetId;
            } else {
                System.out.println("The carpet id that you have entered is not in our system. Please try again");
            }
        }
        return 0;
    }
}
