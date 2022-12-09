package DecoratorPattern;

import Entities.Carpet;


public class FabricBottomLayer extends CustomizedCarpet {

	public FabricBottomLayer(Carpet carpet) {
		super(carpet);
		description = carpet.getName()+" Fabric Bottom Layered Carpet";
	}


	public double getPrice() {
		return carpet.getPrice()+100;
	}
}


