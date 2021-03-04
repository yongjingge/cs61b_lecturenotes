package lecture10;

import lombok.Getter;

import java.util.Comparator;

/* have a nested class 'NameComparator' that implements Comparator interface
* and overrides the compare method */
@Getter
public class Dog2 {

    private String name;
    private int size;

    public Dog2(String name, int size) {
        this.name = name;
        this.size = size;
    }

    /* a NESTED CLASS that implements Comparator,
    * there is no need to instantiate a Dog2 class to actually use NameComparator,
    * so it can be a STATIC class. */
    public static class NameComparator implements Comparator<Dog2> {
        public int compare(Dog2 d1, Dog2 d2) {
            /* String's compareTo */
            return d1.name.compareTo(d2.name);
        }
    }

    /* get a NameComparator */
    public static Comparator<Dog2> getNameComparator() {
        return new NameComparator();
    }

    public static void main(String[] args) {
        Dog2 d1 = new Dog2("a", 11);
        Dog2 d2 = new Dog2("b", 12);
        Comparator<Dog2> nc = getNameComparator();
        if (nc.compare(d1, d2) > 0) {
            System.out.println(d1.name);
        } else {
            System.out.println(d2.name);
        }
    }
}
