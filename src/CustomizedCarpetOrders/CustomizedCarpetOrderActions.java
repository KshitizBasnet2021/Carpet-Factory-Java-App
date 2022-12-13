package CustomizedCarpetOrders;

import DatabaseActions.CustomizedOrderTableActions;
import Entities.CustomizedCarpetOrder;

public class CustomizedCarpetOrderActions {
    CustomizedOrderTableActions customizedOrderTableActions;

    public CustomizedCarpetOrderActions(CustomizedOrderTableActions customizedOrderTableActions) {
        this.customizedOrderTableActions = customizedOrderTableActions;
    }

    public int add() {
        return customizedOrderTableActions.add();
    }

    public CustomizedCarpetOrder get(int id) {
        return customizedOrderTableActions.getOrder(id);
    }

}
