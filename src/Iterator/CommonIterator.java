package Iterator;

import java.util.List;

public class CommonIterator<T> implements Iterator<T> {

    List<T> items;
    int position = 0;

    public CommonIterator(List<?> items) {
        this.items = (List<T>) items;
    }

    public <T> T next() {
        return (T) items.get(position++);
    }

    public boolean hasNext() {
        return items.size() > position;
    }


}
