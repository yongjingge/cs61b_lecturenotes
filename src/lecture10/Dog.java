package lecture10;

/* implements OurComparable interface to have its own compareTo method */
public class Dog implements OurComparable {

    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }
    @Override
    public int compareTo(Object o) {
        Dog otherDog = (Dog) o;
        if (this.size > otherDog.size) {
            return 1;
        } else if (this.size < otherDog.size) {
            return -1;
        }
        return 0;
    }
}
