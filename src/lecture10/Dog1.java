package lecture10;

/* implements a more commonly used built-in Comparable interface */

public class Dog1 implements Comparable<Dog1> {

    private String name;
    private int size;

    public Dog1(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int compareTo(Dog1 other) {
        return this.size - other.size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
