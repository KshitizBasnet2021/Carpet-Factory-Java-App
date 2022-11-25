package FacadePattern;
import Entities.*;
public class CarpetOrderFacade {
	CarpetOrder carpetOrder;

	public CarpetOrderFacade(CarpetOrder carpetOrder) {
		this.carpetOrder = carpetOrder;
	}
 
	public void orderCarpet() {
		carpetOrder.startOrder();
		carpetOrder.getAllItemsInOrder();
	}

	public void changeDeliveryType(boolean type){
		carpetOrder.setDelivery(type);
		orderCarpet();
	}
	public void changeStatusType(CarpetOrder.OrderStatus type){
		carpetOrder.setOrderStatus(type);
		orderCarpet();
	}

	public void displayCarpetsinOrder(){
		carpetOrder.displayAllItemsInOrder();
	}

	public String toString(){
		return carpetOrder.toString();
	}
}
