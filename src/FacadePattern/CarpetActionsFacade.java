package FacadePattern;

//sometimes searched carpet can be ordered automatically
public class CarpetActionsFacade {
	CarpetOrder carpetOrder;
	CarpetSearch carpetSearch;
	public CarpetActionsFacade(CarpetOrder carpetOrder, CarpetSearch carpetSearch) {
		this.carpetOrder = carpetOrder;
		this.carpetSearch = carpetSearch;
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

	//search
	public void searchCarpet() {
		carpetSearch.display();
	}
	public String toString(){
		return carpetOrder.toString();
	}
}
