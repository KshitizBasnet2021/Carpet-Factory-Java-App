package Test.DecoratorTest;

import DecoratorPattern.*;
import Entities.Carpet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecoratorTest {

    @Test
    @DisplayName("Carpet with the bottom layer of fabric")
    void fabricBottomCarpet() {
        CustomizedCarpet customizedCarpet = new FabricBottomLayer(new Carpet(1,"Test",20,2,"30",20));
        CustomizedCarpet goldMaterial = new GoldMaterial(customizedCarpet);
        String goldenCustomizedCarpet = goldMaterial.getDescription();
        double goldenCustomizedCarpetPrice = goldMaterial.getPrice();
        Assertions.assertEquals("Test Fabric Bottom Layered Carpet, Gold", goldenCustomizedCarpet, "Should be");
        Assertions.assertEquals(220, goldenCustomizedCarpetPrice);
    }
    @Test
    @DisplayName("Carpet with the bottom layer of Plastic")
    void plasticBottomCarpet() {
        CustomizedCarpet customizedCarpet = new PlasticBottomLayer(new Carpet(1,"Test",20,2,"30",20));
        CustomizedCarpet woolMaterial = new WoolMaterial(customizedCarpet);
        String woolenCustomizedCarpet = woolMaterial.getDescription();
        double woolenCustomizedCarpetPrice = woolMaterial.getPrice();
        Assertions.assertEquals("Test Plastic Bottom Layered Carpet, Woolen", woolenCustomizedCarpet, "Should be");
        Assertions.assertEquals(40.5, woolenCustomizedCarpetPrice);
    }

    @Test
    @DisplayName("Carpet with the bottom layer of Plastic Wool and Gold")
    void woolandGoldWithPlasticBottomCarpet() {
        CustomizedCarpet customizedCarpet = new PlasticBottomLayer(new Carpet(1,"Test",20,2,"30",20));
        CustomizedCarpet woolMaterial = new WoolMaterial(customizedCarpet);
        CustomizedCarpet goldMaterial = new GoldMaterial(woolMaterial);
        String woolenAndGoldCustomizedCarpet = goldMaterial.getDescription();
        double woolenAndGoldCustomizedCarpetPrice = goldMaterial.getPrice();
        Assertions.assertEquals("Test Plastic Bottom Layered Carpet, Woolen, Gold", woolenAndGoldCustomizedCarpet, "Should be");
        Assertions.assertEquals(140.5, woolenAndGoldCustomizedCarpetPrice);
    }

    @Test
    @DisplayName("Carpet with the bottom layer of Plastic")
    void woolandGoldWithFabricBottomCarpet() {
        CustomizedCarpet customizedCarpet = new PlasticBottomLayer(new Carpet(1,"Test",20,2,"30",20));
        CustomizedCarpet woolMaterial = new WoolMaterial(customizedCarpet);
        CustomizedCarpet goldMaterial = new GoldMaterial(woolMaterial);
        String woolenAndGoldCustomizedCarpet = goldMaterial.getDescription();
        double woolenAndGoldCustomizedCarpetPrice = goldMaterial.getPrice();
        Assertions.assertEquals("Test Plastic Bottom Layered Carpet, Woolen, Gold", woolenAndGoldCustomizedCarpet, "Should be");
        Assertions.assertEquals(140.5, woolenAndGoldCustomizedCarpetPrice);
    }
}
