package lecture37;

import edu.princeton.cs.algs4.Queue;

/* TST (Ternary Search Trie)
* this class represents an symbol table of key-value pairs,
* with string keys and generic values, implemented using a
* ternary search trie.
* It provides character-based methods for finding the string in the symbol table
* that is the longest prefix of a given prefix, and finding all strings in the table
* that start with a given prefix, also finding all strings in the table that match
* a given pattern.
*
* @source https://algs4.cs.princeton.edu/52trie/TST.java.html
* */
public class TST<Value> {
    private Node<Value> root;
    private int n; // size attribute

    /* nested class Node */
    private static class Node<Value> {
        private char c; // each node has a character
        private Node<Value> left, mid, right; // its left, mid, and right sub-tries
        private Value val;
    }

    /* instantiate an empty string symbol table TST */
    public TST() {}

    /* get its size */
    public int getSize() {
        return n;
    }

    /* does the TST contain the given key (string) ?*/
    public boolean containsKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(key) != null;
    }

    /* return value of a given key if exists */
    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length no less than 1");
        }
        Node<Value> res = get(root, key, 0); // searching starts from the root node, given key will be searched from the 0-th digit/character
        if (res == null) {
            return null;
        }
        return res.val;
    }

    /* this get method will return sub-trie corresponding to given key */
    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length no less than 1");
        }
        // searching starts from the d-th character of given string
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) { // when c == x.c yet d is less than the key's length
            return get(x.mid, key, d + 1);
        } else { // when c == x.c and all characters in key have been looked at
            return x;
        }
    }

    /* insert the key-value pair into TST, key is a String we want to insert */
    public void put(String key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (! containsKey(key)) {
            n += 1; // check if TST already has the given key, if not, we can increment the size first
        } else if (val == null) {
            // if given val is null, we will remove key from the table, so we decrement the size
            n -= 1;
        }
        root = put(root, key, val, 0); // insertion starts from the root node
    }

    /* put helper method */
    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d); // key's being inserted from its 0-th character
        /* when current TST has not defined its root node yet */
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            // when the d-th character == x.c yet not reaching the last character of key
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            // when the d-th character == x.c and is the last character of key
            x.val = val;
        }
        return x;
    }

    /* return the string in TST that is the longest prefix of given string,
    * or null if no such string in the table */
    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (query.length() == 0) {
            return null;
        }
        int len = 0; // record the length of string that is the longest prefix of given query
        int i = 0; // i is used to traverse the query
        Node<Value> x = root;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                // when c == x.c
                i += 1; // will traverse the next character of query
                if (x.val != null) {
                    len = i;
                }
                x = x.mid;
            }
        }
        return query.substring(0, len);
    }

    /* return all keys in the table */
    public Iterable<String> keys() {
        Queue<String> res = new Queue<>();
        collect(root, new StringBuilder(), res); // prefix is not defined, meaning that we will return all keys in the table
        return res;
    }

    /* return all keys in the sub-trie rooted at x with given prefix */
    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left, prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    /* return all keys that start with given prefix string */
    public Iterable<String> keyWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("argument is null");
        }
        Queue<String> res = new Queue<>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return res;
        }
        if (x.val != null) {
            res.enqueue(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), res);
        return res;
    }

    /* return all keys that match a given prefix */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> res = new Queue<>();
        collect(root, new StringBuilder(), 0, pattern, res);
        return res;
    }

    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> q) {
        if (x == null) {
            throw new IllegalArgumentException("argument is null");
        }
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) {
            collect(x.left, prefix, i, pattern, q);
        }
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) {
                q.enqueue(prefix.toString() + x.c);
            }
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i + 1, pattern, q);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) {
            collect(x.right, prefix, i, pattern, q);
        }
    }
}
