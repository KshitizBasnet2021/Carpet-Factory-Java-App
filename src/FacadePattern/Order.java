package FacadePattern;
import Entities.*;
public class Order {
	Customer customer;
	Carpet carpet;
	String description;
	public enum OrderStatus {
		Pending,
		Completed,
	}
	OrderStatus orderStatus;
	boolean homeDelivery;
	public Order(Customer customer, Carpet carpet, OrderStatus orderStatus, boolean homeDelivery) {
		this.customer = customer;
		this.carpet = carpet;
		this.orderStatus = orderStatus;
		this.description = customer.getFirstName() + " ordered "+carpet.getName();
		this.homeDelivery = homeDelivery;
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
	public String toString() {
		return description +"\n"+ getStatus() + "\n"+getHomeDelivery();
	}
}
