package Entities;

public class Customer {
    private String first_name;
    private String last_name;
    private Number phone;
    private String user_name;
    private String password;
    private Address address;

    public Customer(String first_name, String last_name, Number phone, String user_name, String password, Address address){
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.user_name = user_name;
        this.password = password;
        this.address = address;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public String getLastName(){
        return  this.last_name;
    }

    public Number getPhone(){
        return this.phone;
    }

    public String getUserName(){
        return this.user_name;
    }

    public String getPassword(){
        return this.password;
    }

    public Address getAddress(){
        return this.address;
    }

    public void setFirstName(String first_name){
         this.first_name = first_name;
    }

    public void setLastName(String last_name){
        this.last_name= last_name;
    }

    public void setPhone(Number phone){
        this.phone = phone;
    }

    public void setUserName(String user_name){
        this.user_name = user_name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAddress(Address address){
        this.address= address;
    }

    public String toString(){
        return ("First Name: "+first_name+
                ", Last Name: "
                +last_name+
                ", Phone: "
                +phone+
                ", User Name:"+
                user_name+
                ", Address: "+
                address.getLine1()+ ", "+address.getCity()+
                ", "+ address.getState()+
                ", "+address.getCountry()+
                ", "+address.getZipCode()
        );
    }
}
