package lecture13;

import java.util.List;

/* Demonstrating Generic type */
public interface Map61B<K, V> {
    /* Returns true if map contains a mapping for the specified key */
    boolean containsKey(K key);

    /* Returns the value to which the specified key is mapped */
    V get(K key);

    /* Returns the number of key-value pairs in this map */
    int getSize();

    /* Associates the specified value with the specified key */
    void put(K key, V value);

    /* Returns a list of keys in this map */
    List<K> getKeys();
}
