package lecture23;

import java.util.ArrayDeque;
import java.util.Queue;

/* A symbol-table implementation with linear-probing hash table.
* LinearProbingHash represents a symbol table of generic K-V pairs.
* @source https://algs4.cs.princeton.edu/34hash/LinearProbingHashST.java.html
* Linear Probing, one of the probing strategies to deal with COLLISIONS.
* This kind of strategy is known as 'Open Addressing', also 'Closed Hashing'.
* */
public class LinearProbingHash<Key, Value> {

    private static final int INIT_CAPACITY = 4;

    private int n; // number of key-value pairs
    private int m; // size of this linear-probing hash table
    private Key[] keys;
    private Value[] values;

    /* Initializes an empty hash table */
    public LinearProbingHash() {
        this(INIT_CAPACITY);
    }

    /* Initializes an empty hash table with
    * specified initial capacity. */
    public LinearProbingHash(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    /* Returns the number of Key-Value pairs in hash table. */
    public int size() {
        return n;
    }

    /* Returns true if the table is empty. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* Returns true if table contains the specified key. */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        return get(key) != null;
    }

    /* Hash Function for Keys
    * Returns value (proper index) between 0 and m-1.
    * m refers to the size of this table. */
    private int hashKey(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /* Resizes the hash table to the given capacity
    * by re-hashing all its keys. */
    private void resize(int capacity) {
        LinearProbingHash<Key, Value> ht = new LinearProbingHash<>(capacity);
        // m is the size of the old hash table
        for (int i = 0; i < m; i += 1) {
            if (keys[i] != null) {
                ht.put(keys[i], values[i]);
            }
        }
        keys = ht.keys;
        values = ht.values;
        m = ht.m;
    }

    /* Inserts specified Key-Value pair into the hash table.
    * Overwrite the old value if key exists.
    * Delete the key if value provided is null. */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Argument key is null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        // double table size if 50% is full
        if (n >= m / 2) {
            resize(m * 2);
        }

        // if key exists
        int i;
        for (i = hashKey(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n += 1;
    }

    /* Returns the value associated with specified key. */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        for (int i = hashKey(key); keys[i] != null; i = (1 + 1) % m) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    /* Removes the specified key and its associated value.
    * Notice that we need to rehash all keys in the same cluster after removal. */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        // find position for given key
        int i = hashKey(key);
        while (! keys[i].equals(key)) {
            i = (i + 1) % m;
        }
        // delete
        keys[i] = null;
        values[i] = null;

        // rehash all keys in the same cluster
        /* because we have removed the i-th Key-Value pair,
        * we have empty space for rehashing.
        * the next pair at position (i + 1)  m can be rehashed.*/
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRehash = keys[i];
            Value valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            // when we put this pair later, we will increment n by 1,
            // so here we need to first decrement n;
            n -= 1;
            put(keyToRehash, valueToRehash);

            i = (i + 1) % m;
        }
        n -= 1;

        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

    /* Returns all keys in this hash table as an Iterable. */
    public Iterable<Key> keys() {
        Queue<Key> keyQueue = new ArrayDeque<>();
        for (int i = 0; i < m; i += 1) {
            if (keys[i] != null) {
                keyQueue.add(keys[i]);
            }
        }
        return keyQueue;
    }

}
