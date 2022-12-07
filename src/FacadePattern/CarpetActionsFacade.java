package FacadePattern;
//sometimes searched carpet can be ordered automatically
public class CarpetActionsFacade {
	CarpetOrder carpetOrder;
	CarpetSearch carpetSearch;
	CarpetActions carpetActions;
	public CarpetActionsFacade(CarpetActions carpetActions, CarpetOrder carpetOrder, CarpetSearch carpetSearch) {
		this.carpetOrder = carpetOrder;
		this.carpetSearch = carpetSearch;
		this.carpetActions = carpetActions;

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

	//carpets crud
	public int addCarpet() {
		return carpetActions.add();
	}

	public void update(int carpetId) {
		carpetActions.update(carpetId);
	}

	public void delete(int carpetId) {
		carpetActions.delete(carpetId);
	}
	public String toString(){
		return carpetOrder.toString();
	}

}
