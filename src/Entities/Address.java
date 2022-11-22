package Entities;

public class Address {
    private String line1;
    private String city;
    private String state;
    private String country;
    private Number zipCode;

    public Address(String line1, String city, String state, String country, Number zipCode){
        this.line1 = line1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getLine1(){
        return line1;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getCountry(){
        return country;
    }

    public Number getZipCode(){
        return zipCode;
    }

    public void setLine1(String line1){
        this.line1 = line1;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setZipCode(Number zipCode){
        this.zipCode = zipCode;
    }
}
