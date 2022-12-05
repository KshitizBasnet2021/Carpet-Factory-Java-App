package Iterator;

public interface Iterator<T> {
    boolean hasNext();
    <T> T next();
}
