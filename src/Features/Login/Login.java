package Features.Login;

import DatabaseActions.CustomerTableActions;

import java.sql.Connection;

public class Login {
    String username;
    String password;
    String type;

    Connection con;

    public Login(String userName, String password, String type, Connection con){
      this.username = userName;
      this.password = password;
      this.type = type;
      this.con = con;
    }

    public int getCustomerID(){
            CustomerTableActions customerTableActions = new CustomerTableActions(null, con);
            return customerTableActions.customerLogin(this.username, this.password);
    }
    public int getAdminLogin() {
            if (username.equals("admin") && password.equals("admin")) {
                return 1;
            }
        return 0;
    }

}
