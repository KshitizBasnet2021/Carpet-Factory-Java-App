package Entities;

public class Carpet {
    private int carpetId;
    private String name;
    private double height;
    private double width;
    private String material;
    private double price;

    public Carpet(int carpetId, String name, double height, double width, String material, double price){
        this.carpetId =carpetId;
        this.name = name;
        this.height = height;
        this.width= width;
        this.material = material;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }

    public String getMaterial(){
        return this.material;
    }
    public double getPrice(){
        return this.price;
    }
    public double getCarpetId(){
        return this.carpetId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public void setCarpetId(int carpetId){this.carpetId = carpetId;}

    public void setPrice(double price){
        this.price = price;
    }
    public String toString(){
        return "Name: "+name +", "+
                "Height: "+height+", "+
                "Width: "+width+", "+
                "Material: "+material+","+
                "Price: $"+price;

    }
}
