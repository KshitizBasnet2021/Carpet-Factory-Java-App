package FacadePattern;

//sometimes searched carpet can be ordered or customized automatically for the future
public class CarpetActionsFacade {
    CarpetOrder carpetOrder;


    public CarpetActionsFacade(CarpetOrder carpetOrder) {
        this.carpetOrder = carpetOrder;

    }

    public void orderCarpet() {
        if (!carpetOrder.equals(null)) {
            carpetOrder.startOrder();
            carpetOrder.getAllItemsInOrder();
        }
        System.out.println("Cannot invoke order carpet as it is null");
    }


    public void displayCarpetsinOrder() {
        if (!carpetOrder.equals(null)) {
            carpetOrder.displayAllItemsInOrder();
        } else {
            System.out.println("Cannot invoke display carpet as it is null");
        }
    }



    public String toString() {
        return carpetOrder.toString();
    }

}
