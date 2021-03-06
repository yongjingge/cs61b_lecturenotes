package lecture13;

import java.util.ArrayList;
import java.util.List;

public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
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
        if (index > -1) {
            return values[index];
        }
        return null;
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

    public static void main(String[] args) {
        ArrayMap<String, Integer> map = new ArrayMap<>();
        map.put("fish", 8);
        map.put("cat", 1);
        map.put("dog", 22);
        System.out.println(map.getSize());
        System.out.println(map.containsKey("cat"));
        System.out.println(map.get("fish"));
        System.out.println(map.getKeys().indexOf("dog"));
    }
}
