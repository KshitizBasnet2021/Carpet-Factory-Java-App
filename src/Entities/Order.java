package Entities;

import java.util.Date;

public class Order {
    private String orderDate;
    private int orderId;
    public Order(int orderId, String orderDate){
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public String getOrderdate(){
        return this.orderDate;
    }

    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }

    public int getOrderId(){
        return this.orderId;
    }
}
