package Test.TableActionsTests;

import DatabaseActions.CartTableActions;
import DatabaseActions.CustomerTableActions;
import DatabaseActions.OrderTableActions;
import Entities.Cart;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

public class CartTableActionsTest {

    @Test
    @DisplayName("Cart Addition Test")
    void TestCartCreation()
    {
        String customer_user_name = "Test_username2";
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        int carpetId = 1;
        CustomerTableActions customerTableActions = new CustomerTableActions(null, con);
        int customerId = customerTableActions.getIDFromUserName(customer_user_name);
        CartTableActions cartTableActions = new CartTableActions(con, customerId, orderId, carpetId);
        int cartId = cartTableActions.add();
        Assertions.assertEquals(cartTableActions.getLastCreatedCartId(), cartId, "They should match");
    }

    @Test
    @DisplayName("Cart items in single order")
    void GetItemsInSingleOrder(){
        String customer_user_name = "Test_username2";
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        OrderTableActions orderTableActions = new OrderTableActions(con);
        int orderId = orderTableActions.add();
        int carpetId = 1;
        CustomerTableActions customerTableActions = new CustomerTableActions(null, con);
        int customerId = customerTableActions.getIDFromUserName(customer_user_name);
        CartTableActions cartTableAction1 = new CartTableActions(con, customerId, orderId, carpetId);
        CartTableActions cartTableAction2 = new CartTableActions(con, customerId, orderId, carpetId);

        int cartId1 = cartTableAction1.add();
        int cartId2 = cartTableAction2.add();
        ArrayList<Cart> carts = cartTableAction1.getCartsFromOrderId(orderId);
        ArrayList<Cart> expectedArraylist = new ArrayList<>();
        expectedArraylist.add(new Cart(cartId1, customerId, carpetId, orderId));
        expectedArraylist.add(new Cart(cartId2, customerId, carpetId, orderId));
        Assertions.assertEquals(expectedArraylist.toString(), carts.toString(),"Should Match");
    }
}
