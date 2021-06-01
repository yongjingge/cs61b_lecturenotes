package lecture23;

import java.util.ArrayDeque;
import java.util.Queue;

public class LPH<K, V> {

    private static final int INITCAP = 4;
    private int n; // number of K-V pairs in the table
    private int m; // table size
    private K[] keys;
    private V[] values;

    public LPH() {
        this(INITCAP);
    }

    public LPH(int capacity) {
        m = capacity;
        n = 0;
        keys = (K[]) new Object[m];
        values = (V[]) new Object[m];
    }

    public int getSize() {
        return n;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        return get(key) != null;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        for (int index = hash(key); keys[index] != null; index = (index + 1) % m) {
            if (keys[index].equals(key)) {
                return values[index];
            }
        }
        return null;
    }

    private void resize(int newsize) {
        LPH<K, V> resized = new LPH<>(newsize);
        for (int i = 0; i < m; i += 1) {
            if (keys[i] != null) {
                resized.put(keys[i], values[i]);
            }
        }
        keys = resized.keys;
        values = resized.values;
        m = resized.m;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        if (n > m / 2) {
            resize(m * 2);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        // empty entry in the same cluster
        keys[i] = key;
        values[i] = value;
        n += 1;
    }

    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int i = hash(key);
        while(! keys[i].equals(key)) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        values[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) { // rehash pairs in its cluster
            K keyToRehash = keys[i];
            V valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            n -= 1;

            put(keyToRehash, valueToRehash);
            i = (i + 1) % m;
        }

        n -= 1;
        if ( n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

    public Iterable<K> getKs() {
        Queue<K> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i += 1) {
            if (keys[i] != null) {
                queue.add(keys[i]);
            }
        }
        return queue;
    }
}
