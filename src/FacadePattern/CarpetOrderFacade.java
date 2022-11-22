package FacadePattern;
import Entities.*;
public class CarpetOrderFacade {
	Order order;
	Address address;
 
	public CarpetOrderFacade(Order order, Address address) {
		this.order = order;
		this.address = address;
	}
 
	public String orderCarpet() {
		return (order.toString());
	}

	public void changeDeliveryType(boolean type){
		order.setDelivery(type);
		orderCarpet();
	}
	public void changeStatusType(Order.OrderStatus type){
		order.setOrderStatus(type);
		orderCarpet();
	}
	public String getOrderAddress(){
		return address.getLine1()+", "+ address.getCity()+ ", "+ address.getState() + ", "+ address.getCountry();
	}
	public String toString(){
		return "Order:"+ order;
	}
}
