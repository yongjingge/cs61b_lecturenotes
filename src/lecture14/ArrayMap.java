package lecture14;

import lecture13.Map61B;

import java.util.ArrayList;
import java.util.List;

/* ArrayMap with KeyIterator */
public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /* create a KeyIterator class (a Nested Class) that allows us to iterate through the keys of an ArrayMap.
    * the Iterator should have 'next()' and 'hasNext()' methods.
    * KeyIterator is not STATIC, because we need to iterate keys of a specific ArrayMap instance! */
    public class KeyIterator {
        private int pos;
        public KeyIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < size;
        }
        public K next() {
            K returned = keys[pos];
            pos += 1;
            return returned;
        }
    }

    /* we can make the initialization of KeyIterator very much like the list.iterator() method */
    public KeyIterator getKeyIterator() {
        return new KeyIterator();
    }

    /* helper method to get a specified key's index if exists,
     * if not, return -1 */
    private int keyIndex(K key) {
        for (int i = 0; i < size; i += 1) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsKey(K key) {
        return keyIndex(key) > -1;
    }

    @Override
    public V get(K key) {
        int index = keyIndex(key);
        /* Exception Handler */
        if (index == -1) {
            throw new IllegalArgumentException("The key provided " + key + " was not in the ArrayMap.");
        }
        return values[index];
//        if (index > -1) {
//            return values[index];
//        }
//        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int index = keyIndex(key);
        /* if ArrayMap doesn't have the specified key,
         * we put the KV pair into the ArrayMap as a new input. */
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
            return;
        }
        values[index] = value;
    }

    @Override
    public List<K> getKeys() {
        List<K> res = new ArrayList<>();
        /* we use 'i < size' in the for loop rather than
         * 'i < keys.length', this will avoid adding undefined items of the keys array into our result. */
        for (int i = 0; i < size; i += 1) {
            res.add(keys[i]);
        }
        return res;
    }

}
