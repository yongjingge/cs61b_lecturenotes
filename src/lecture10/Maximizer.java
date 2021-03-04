package lecture10;

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

    public static void main(String[] args) {
        Dog[] dogs = {new Dog("a", 1), new Dog("b", 3), new Dog("c", 22)};
        Dog max = (Dog) max(dogs);
        System.out.println(max.getName());
    }

}
