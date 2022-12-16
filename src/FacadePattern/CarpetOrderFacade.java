package FacadePattern;

import Entities.Address;
import Entities.Cart;
import Entities.Customer;

import java.util.ArrayList;
import java.util.Scanner;

//sometimes searched carpet can be ordered or customized automatically for the future
public class CarpetOrderFacade {
    CarpetOrder carpetOrder;
    Delivery delivery;
    Packaging packaging;


    public CarpetOrderFacade(CarpetOrder carpetOrder, Delivery delivery, Packaging packaging) {
        this.carpetOrder = carpetOrder;
        this.delivery = delivery;
        this.packaging = packaging;
    }

    public void orderCarpet() {
        if (!carpetOrder.equals(null)) {
            carpetOrder.startOrder();

            //start packaging
             packaging.add();
            // start delivery
        }else {
            System.out.println("Cannot invoke order carpet as it is null");
        }
    }

    public void deliver(){
        carpetOrder.displayAllItemsInOrder();
        delivery.start(carpetOrder.getCustomerDetails());
    }

    public void changetoHomeDelivery(){
        this.delivery.changeDelivery();
    }

    //interface to getting all carts
    public ArrayList<Cart> getAllCarts(){
        return carpetOrder.getAllItemsInOrder();
    }
    public String toString() {
        return carpetOrder.toString();
    }

}
