package Iterator;

import java.util.ArrayList;

public class CommonList implements IteratorCreator {
    ArrayList<?> commonList;

    public CommonList(ArrayList<?> commonList) {
        this.commonList = commonList;
    }

    public Iterator createIterator() {
        return new CommonIterator(commonList);
    }
}
