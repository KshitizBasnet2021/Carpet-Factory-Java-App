import FacadePattern.CarpetActionsFacade;
import FacadePattern.CarpetSearch;
import Singleton.DatabaseConnection;

import java.sql.Connection;

public class Main {

    public Main() {}

    public static void main(String[] Args){
        while(true) {
            new AuthenticationUI();
        }
    }

}