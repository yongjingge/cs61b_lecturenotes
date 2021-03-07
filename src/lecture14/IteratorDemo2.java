package lecture14;

public class IteratorDemo2 {

    public static void main(String[] args) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("fish", 8);
        map.put("cat", 1);
        map.put("dog", 22);

        /* KeyIterator is a nested class in ArrayMap.
        * it is not static.
        * we need to instantiate a KeyIterator through an ArrayMap instance. */
        ArrayMap.KeyIterator keyIterator = map.new KeyIterator();
        while (keyIterator.hasNext()) {
            System.out.println(keyIterator.next());
        }
    }
}
