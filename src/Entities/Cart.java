package Entities;

public class Cart {
    private int cartId;
    private int customerId;
    private int orderID;

    private int carpetId;

    public Cart(int cartId, int customerId, int carpetId, int orderId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.carpetId = carpetId;
        this.orderID = orderId;
    }

    public int getCartId(){
        return cartId;
    }

    public int getCustomerId(){
        return customerId;
    }

    public int getOrderId(){
        return orderID;
    }

    public int getCarpetIdId(){
        return carpetId;
    }
}
