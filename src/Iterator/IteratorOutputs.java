package Iterator;

public class IteratorOutputs {
    Iterator<?> iterator;

    public IteratorOutputs(Iterator<?> iterator){
        this.iterator = iterator;
    }

    public void displayData() {
        int i = 1;
        while (iterator.hasNext()) {
            System.out.print(i + ". ");
            System.out.println(iterator.next().toString());
            i = i + 1;
        }
    }
}
