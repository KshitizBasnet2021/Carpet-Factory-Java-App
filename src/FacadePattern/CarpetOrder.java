package FacadePattern;
import DatabaseActions.CartTableActions;
import Entities.*;

import java.sql.Connection;
import java.util.ArrayList;

public class CarpetOrder{

	CartTableActions cartTableAction;
	OrderStatus orderStatus;
	boolean homeDelivery;
	int carpetId;
	public CarpetOrder(int customerId, int orderId, int carpetId, Connection con){
		this.carpetId = carpetId;
		this.cartTableAction = new CartTableActions(con, customerId, orderId, carpetId);
	}
	public enum OrderStatus {
		Pending,
		Completed,
	}

	private String getStatus(){
		if(orderStatus.equals(OrderStatus.Completed)){
			return "Completed Order";
		}
		return "Pending Order";
	}
	private String getHomeDelivery(){
		if(homeDelivery){
			return "Customer Asked for Home Delivery";
		}
		return "In Store Pickup";
	}
	public void setDelivery(boolean type){
		this.homeDelivery = type;
	}
	public void setOrderStatus(OrderStatus type){
		this.orderStatus = type;
	}

	public void startOrder(){
		this.cartTableAction.add();
	}
	public void displayAllItemsInOrder(){
		int orderId = this.cartTableAction.getOrderId();
		ArrayList<Cart> carts = this.cartTableAction.getCartsFromOrderId(orderId);
		System.out.println("The orders in the cart are as follows:");
		for (int i = 0; i < carts.size(); i++) {
			System.out.println(carts.get(i) + " ");
		}
	}

	public ArrayList<Cart> getAllItemsInOrder(){
		int orderId = this.cartTableAction.getOrderId();
		ArrayList<Cart> carts = this.cartTableAction.getCartsFromOrderId(orderId);
		return  carts;
	}


	public String toString() {
	  String details = String.valueOf(cartTableAction.getOrderdCarpet(carpetId));
	  return details;
	}
}
