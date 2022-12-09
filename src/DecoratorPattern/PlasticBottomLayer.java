package DecoratorPattern;

import Entities.Carpet;

public class PlasticBottomLayer extends CustomizedCarpet {

    public PlasticBottomLayer(Carpet carpet) {
        super(carpet);
        description = carpet.getName() + " Plastic Bottom Layered Carpet";
    }

    public double getPrice() {
        return carpet.getPrice() + 20;
    }
}

