package DecoratorPattern;

public abstract class CarpetDecorator extends CustomizedCarpet {
	CustomizedCarpet customizedCarpet;
	public CarpetDecorator(CustomizedCarpet customizedCarpet) {
		super(customizedCarpet.carpet);
		this.customizedCarpet = customizedCarpet;
	}

	public abstract String getDescription();
}
