package Entities;

public class CustomizedCarpetOrder {
    private final int orderId;
    private final int customerId;
    private final String orderDate;
    private final int carpetId;
    private final String description;
    private final double price;

    public CustomizedCarpetOrder(int orderId, String orderDate, int customerId, int carpetId, String description, double price) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.carpetId = carpetId;
        this.description = description;
        this.price = price;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public int getCarpetId() {
        return this.carpetId;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }
    public String toString() {
        return "Customized Order ID:"+ this.orderId +",Order Date: "+ this.orderDate + ",Carpet ID: "+this.carpetId +",Customer ID: "+this.customerId + ", Description: "+this.description+", Price: "+this.price;
    }
}
