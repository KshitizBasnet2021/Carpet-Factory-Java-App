package FacadePattern;
import DatabaseActions.CartTableActions;
import Entities.*;
import Iterator.CommonList;
import Iterator.Iterator;
import Iterator.IteratorOutputs;

import java.sql.Connection;
import java.util.ArrayList;

public class CarpetOrder{

	CartTableActions cartTableAction;
	int carpetId;
	int customerId;
	public CarpetOrder(int customerId, int orderId, int carpetId, Connection con){
		this.carpetId = carpetId;
		this.cartTableAction = new CartTableActions(con, customerId, orderId, carpetId);
		this.customerId = customerId;
	}

	public void startOrder(){
		this.cartTableAction.add();
	}

	public void displayAllItemsInOrder(){
		ArrayList<Cart> carts = getAllItemsInOrder();
		CommonList cartList = new CommonList(carts);
		Iterator<Cart> cartListIterator = cartList.createIterator();

		//just generate output of addresses
		IteratorOutputs iteratorOutputs = new IteratorOutputs(cartListIterator);
		iteratorOutputs.displayData();
	}

	public ArrayList<Cart> getAllItemsInOrder(){
		int orderId = this.cartTableAction.getOrderId();
		ArrayList<Cart> carts = this.cartTableAction.getCartsFromOrderId(orderId);
		return carts;
	}

	public Customer getCustomerDetails(){
		return cartTableAction.getCustomerFromCustomerID(customerId);
	}

	public String toString() {
	  String details = String.valueOf(cartTableAction.getOrderdCarpet(carpetId));
	  return details;
	}
}
