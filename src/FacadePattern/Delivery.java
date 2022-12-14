package FacadePattern;

import Entities.Address;
import Entities.Customer;

public class Delivery {
    boolean inStoreDelivery;

    public Delivery(boolean homeDelivery){
        inStoreDelivery = !homeDelivery;
    }

    public void start(Customer customer){
        if(inStoreDelivery){
            System.out.println("Sold to customer");
        }
        else {
            System.out.println("Home Delivery started");
            System.out.println("Customer and Address Details");
            System.out.println(customer);
            System.out.println("Expected delivery date: 2-3 business days");
        }
    }
    public void changeDelivery(){
       this.inStoreDelivery = !this.inStoreDelivery;
    }
}
