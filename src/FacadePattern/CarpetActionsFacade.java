package FacadePattern;

import CarpetCRUDActions.CarpetActions;
import Entities.CustomizedCarpetOrder;

//sometimes searched carpet can be ordered or customized automatically for the future
public class CarpetActionsFacade {
    CarpetOrder carpetOrder;
    CarpetSearch carpetSearch;

    CustomizedCarpetOrderActions customizedCarpetOrderActions;

    public CarpetActionsFacade(CarpetOrder carpetOrder, CarpetSearch carpetSearch, CustomizedCarpetOrderActions customizedCarpetOrderActions) {
        this.carpetOrder = carpetOrder;
        this.carpetSearch = carpetSearch;
        this.customizedCarpetOrderActions = customizedCarpetOrderActions;

    }

    public void orderCarpet() {
        if (!carpetOrder.equals(null)) {
            carpetOrder.startOrder();
            carpetOrder.getAllItemsInOrder();
        }
        System.out.println("Cannot invoke order carpet as it is null");
    }


    public void displayCarpetsinOrder() {
        if (!carpetOrder.equals(null)) {
            carpetOrder.displayAllItemsInOrder();
        } else {
            System.out.println("Cannot invoke display carpet as it is null");
        }
    }

    //search
    public void searchCarpet() {
        if (!carpetSearch.equals(null)) {
            carpetSearch.display();
        } else {
            System.out.println("Cannot invoke search carpet as it is null");
        }
    }

    //customized carpet
    public int addCustomizedOrder() {
        if (!customizedCarpetOrderActions.equals(null))
            return customizedCarpetOrderActions.add();
        else {
            System.out.println("Cannot invoke adding customized  carpet order as it is null");
            return 0;
        }
    }

    public CustomizedCarpetOrder getCustomizedOrder(int id) {
        if (!customizedCarpetOrderActions.equals(null)) {
            return customizedCarpetOrderActions.get(id);
        } else {
            System.out.println("Cannot invoke order carpet as it is null");
            return null;
        }
    }


    public String toString() {
        return carpetOrder.toString();
    }

}
