package lecture13;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/* MapHelper has two methods:
* get(Map61B, K)
* maxKey(Map61B) */
public class MapHelper {

    /* 'get' is a static method.
    * we don't need to make the whole class MapHelper be Generic.
    * we make this get method be Generic!
    *
    * right after the 'static' keyword and before the 'return type', we
    * define the type parameters of this method, and it will be available within
    * this method.
    *
    * in this case, we return a V type, the Generic type is <K, V> */
    public static <K, V> V get(Map61B<K, V> map, K key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    @Test
    public void testGet() {
        Map61B<String, Integer> map = new ArrayMap<>();
        map.put("dog", 2);
        map.put("cat", 11);
        map.put("fish", 25);

        Integer expected = 11;
        Integer actual = MapHelper.get(map, "cat");
        assertEquals(expected, actual);
    }

    /* Generic type is <K, V>,
    * we also need K to be comparable, so K needs to extend Comparable.
    * this is called 'type upper bounds'.
    * we use "extends" to inherit an Interface because using "implements" will require
    * the concrete class to inherit all of the interface's methods/attributes. */
    public static <K extends Comparable<K>,V> K maxKey(Map61B<K, V> map) {
        List<K> keys = map.getKeys();
        K largest = keys.get(0);
        for (K singleK : keys) {
            if (singleK.compareTo(largest) > 0) {
                largest = singleK;
            }
        }
        return largest;
    }

    @Test
    public void testMax() {
        Map61B<String, Integer> map = new ArrayMap<>();
        map.put("dog", 2);
        map.put("cat", 11);
        map.put("fish", 25);
        map.put("goose", 13);

        String expected = "goose";
        String actual = MapHelper.maxKey(map);
        assertEquals(expected, actual);
    }
}
