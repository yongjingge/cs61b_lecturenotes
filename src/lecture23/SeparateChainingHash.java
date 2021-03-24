package lecture23;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Queue;

/* A symbol table implemented with a separate-chaining hash-table
* @source https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
* Separate chaining: also called 'external chaining', known as an Open Hashing strategy.
* */
public class SeparateChainingHash<Key, Value> {

    private static final int INIT_CAPACITY = 4;
    private int n; // number of key-value pairs
    private int bucket; // number of hash table containers
    private SequentialSearchST<Key, Value>[] st; // array of linked-list symbol tables

    /* Initializes an empty symbol table. */
    public SeparateChainingHash() {
        this(INIT_CAPACITY);
    }

    /* Initializes an empty symbol table with m chains. */
    public SeparateChainingHash(int m) {
        bucket = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i += 1) {
            st[i] = new SequentialSearchST<>(); // each hash index be a linked-list symbol table
        }
    }

    /* Resizes the hash table to have given number of chains,
    * and rehashes all the keys */
    private void resize(int newSize) {
        SeparateChainingHash<Key, Value> temp = new SeparateChainingHash<>(newSize);
        // bucket is the old number of chains,
        // newSize is the new number of chains we want to have
        for (int i = 0; i < bucket; i += 1) {
            // for a given index of the array, we get all the Ks of the same hashCode()
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.n = temp.n;
        this.bucket = temp.bucket;
        this.st = temp.st;
    }

    /* Hash Function for Ks,
    * Returns value between 0 and m-1,
    * to find the bucket number. */
    private int hashKs(Key key) {
        return (key.hashCode() & 0x7fffffff) % bucket;
    }

    /* Returns the number of K-V pairs in this symbol table. */
    public int size() {
        return n;
    }

    /* Returns true if this symbol table is empty. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* Returns the value associated with the specified key in this
    * symbol table. */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        /* return the hashed result of given key */
        int i = hashKs(key);
        return st[i].get(key);
    }

    /* Returns true if this symbol table contains the specified key. */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        return get(key) != null;
    }

    /* Inserts the specified K-V pairs into the symbol table,
    * overwriting the old value with the new value if key exists,
    * deletes the specified key and its associated value
    * if the specified value is null. */
    public void put(Key key, Value newValue) {
        if (key == null) {
            throw new IllegalArgumentException("First argument is null");
        }
        if (newValue == null) {
            delete(key);
            return;
        }
        if (n >= 10 * bucket) {
            resize(2 * bucket);
        }

        int i = hashKs(key);
        /* if the key does not exist, we need to add it as a new KV pair,
        * so we need to increment the number of KV pairs by 1. */
        if (! st[i].contains(key)) {
            n += 1;
        }
        st[i].put(key, newValue);
    }

    /* Removes the specified key and its associated value
    * if the key exists. */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        int i = hashKs(key);
        if (st[i].contains(key)) {
            n -= 1;
        }
        st[i].delete(key);
        if (bucket > INIT_CAPACITY && n <= 2 * bucket) {
            resize(bucket / 2);
        }
    }

    /* Returns keys as an Iterable */
    public Iterable<Key> keys() {
        Queue<Key> keysQueue = new ArrayDeque<>();
        for (int i = 0; i < bucket; i+= 1) {
            for (Key key : st[i].keys()) {
                keysQueue.add(key);
            }
        }
        return keysQueue;
    }

    public static void main(String[] args) {
        SeparateChainingHash<String, Integer> st = new SeparateChainingHash<>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
