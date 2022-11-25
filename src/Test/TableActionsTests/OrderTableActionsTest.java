package Test.TableActionsTests;

import DatabaseActions.OrderTableActions;
import Singleton.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class OrderTableActionsTest {

    @Test
    @DisplayName("Order Addition Test")
    void TestOrderCreation()
    {
        Connection con = DatabaseConnection.getInstance(false).getConnection();
        OrderTableActions OrderTableActions = new OrderTableActions(con);
        int orderId = OrderTableActions.add();
        Assertions.assertEquals(OrderTableActions.getLastCreatedOrderId(),
                orderId, "They should Match");
    }

}
