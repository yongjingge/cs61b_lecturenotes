package lecture10;

import java.util.Arrays;
import java.util.Collections;

/* provide a max method */
public class Maximizer {

    public static OurComparable max(OurComparable[] os) {
        int maxIndex = 0;
        for (int i = 1; i < os.length; i += 1) {
            int cmp = os[i].compareTo(os[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }
        return os[maxIndex];
    }

    public static Comparable max(Comparable[] os) {
        int maxIndex = 0;
        for (int i = 1; i < os.length; i += 1) {
            int cmp = os[i].compareTo(os[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }
        return os[maxIndex];
    }

    public static void main(String[] args) {
//        Dog[] dogs = {new Dog("a", 1), new Dog("b", 3), new Dog("c", 22)};
//        Dog max = (Dog) max(dogs);
//        System.out.println(max.getName());

        Dog1[] dogs = {new Dog1("a", 1), new Dog1("b", 3), new Dog1("c", 22)};
        Dog1 max = (Dog1) max(dogs);
        System.out.println(max.getName());

        Dog1 largest = Collections.max(Arrays.asList(dogs));
        System.out.println(largest.getName());
    }

}
