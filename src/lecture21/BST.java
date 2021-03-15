package lecture21;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/* Binary Search Tree Implementation
* @source https://algs4.cs.princeton.edu/32bst/BST.java.html
* */
public class BST<K extends Comparable<K>, V> {
    /* root of BST */
    private Node root;

    /* Nested Class: Node */
    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left;
        private Node right;

        public Node(K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }
    }

    /* Initializes an empty symbol table */
    public BST() {
    }

    /* return the number of K-V pairs in BST */
    public int size() {
        return size(root); // size(Node x) as helper method
    }
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /* check if BST is empty */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* check if BST contains a given K */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(key) != null; // get(K k) as helper method
    }

    /* returns the value associated with the given key */
    public V get(K key) {
        return get(root, key); // get(Node x, K k) as helper method
    }
    /* using Binary Search to find the given key, then return its value */
    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    /* Insert the specified K-V pair into BST,
    * overwriting the old value with the new value if BST already contains the given key.
    * Delete the specified K (and its value) if the specified value is null. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        /* if specified value is null, we need to remove that key as well. */
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value); // put(Node x, K k, V v) as helper method, will return Node
    }
    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.value = value;
        }
        /* important: remember to change x's size */
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* remove the specified K-V pair if the K exists */
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with a null key");
        }
        root = delete(root, key); // delete(Node x, K k) as helper method
    }
    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            x.left = delete(x.left, key);
        } else {
            // deleting the Node itself: now x.key == key
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            /* Node x has both left and right children */
            Node p = x;
            x = min(p.right); // setting either the largest from leftsub or the smallest from rightsub as the new root
            x.right = deleteMin(p.right);
            x.left = p.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* return the smallest K */
    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty BST");
        }
        return min(root).key; // return the min NODE.key
    }
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /* remove the smallest K in BST */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls deleteMin() with empty BST");
        }
        root = deleteMin(root); // helper method
    }
    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right; // meaning Node x itself as the smallest is removed
        }
        x.left = deleteMin(x.left);
        /* remember to change x's size after removal */
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* return the height of BST */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /* return the Ks in BST in Level Order */
    public Iterable<K> levelOrder() {
        Queue<K> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();
        nodes.enqueue(root);
        while (! nodes.isEmpty()) {
            Node x = nodes.dequeue();
            if (x == null) {
                continue;
            }
            keys.enqueue(x.key);
            nodes.enqueue(x.left);
            nodes.enqueue(x.right);
        }
        return keys;
    }
}
