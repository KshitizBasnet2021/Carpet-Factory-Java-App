package FacadePattern;

public class Packaging {
    String description;
    public Packaging(){
        this.description = "Starting Packaging";
    }
    public void add(){
        description = description + "\nPackaging successful";
        System.out.println(description);
    }
    public void removePackaging(){
        if(description.contains("\n")) {
            System.out.println("Removing Packaging");
            description = "Nothing to package";
        }
    }
    public String toString(){
        return this.description;
    }
}
