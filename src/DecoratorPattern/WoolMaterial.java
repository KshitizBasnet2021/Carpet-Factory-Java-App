package DecoratorPattern;

public class WoolMaterial extends CarpetDecorator {
    CustomizedCarpet customizedCarpet;

    public WoolMaterial(CustomizedCarpet customizedCarpet) {
        super(customizedCarpet);
        this.customizedCarpet = customizedCarpet;
    }

    public String getDescription() {
        return customizedCarpet.getDescription() + ", Woolen";
    }

    public double getPrice() {
        return customizedCarpet.getPrice() + 0.5;
    }
}
