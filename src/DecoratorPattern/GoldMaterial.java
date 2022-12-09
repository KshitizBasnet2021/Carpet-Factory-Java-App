package DecoratorPattern;

public class GoldMaterial extends CarpetDecorator {
	CustomizedCarpet customizedCarpet;
	public GoldMaterial(CustomizedCarpet customizedCarpet) {
		super(customizedCarpet);
		this.customizedCarpet = customizedCarpet;
	}

	public String getDescription() {
		return customizedCarpet.getDescription() + ", Gold";
	}
 
	public double getPrice() {
		return customizedCarpet.getPrice() + 100;
	}
}
