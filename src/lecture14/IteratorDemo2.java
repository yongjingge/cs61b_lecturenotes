package lecture14;

/* KeyIterator has to be initialized through an instance of ArrayMap! */
public class IteratorDemo2 {

    public static void main(String[] args) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("fish", 8);
        map.put("cat", 1);
        map.put("dog", 22);

        /* KeyIterator is a nested class in ArrayMap.
        * it is not static.
        * we need to instantiate a KeyIterator through an ArrayMap instance. */
        ArrayMap.KeyIterator keyIterator1 = map.new KeyIterator();

        /* we can make the initialization of KeyIterator very much like the List.iterator() method */
        ArrayMap.KeyIterator keyIterator2 = map.getKeyIterator();

        while (keyIterator1.hasNext()) {
            System.out.println(keyIterator1.next());
        }

        while (keyIterator2.hasNext()) {
            System.out.println(keyIterator2.next());
        }
    }
}
