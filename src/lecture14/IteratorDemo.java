package lecture14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* using iterator() method to iterate a List */
public class IteratorDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(42);
        list.add(5);

        /* a List has a 'iterator()' method,
        * an Iterator has 'hasNext()' and 'next()' methods. */
        Iterator<Integer> seer = list.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }
    }
}
