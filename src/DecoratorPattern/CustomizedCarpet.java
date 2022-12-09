package DecoratorPattern;

import Entities.Carpet;

public abstract class CustomizedCarpet {
	Carpet carpet;
	String description;
	double price;
	public CustomizedCarpet(Carpet carpet){
		this.carpet = carpet;
		this.description = carpet.getName();
		this.price = carpet.getPrice();
	}

	public String getDescription() {
		return description;
	}
 
	public  double getPrice(){
		return this.price;
	};
}
