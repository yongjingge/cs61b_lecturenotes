package lecture38;

import edu.princeton.cs.algs4.MinPQ;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* This class provides a data structure to hold the Decoding Trie data.
*  */
public class BinaryTrie implements Serializable {

    private Node root;

    /* constructor: build a trie using the given frequency table */
    public BinaryTrie(Map<Character, Integer> freqTable) {
        this.root = buildTrie(freqTable); // helper method 'buildTrie' need the frequency table argument
    }

    /* helper method: buildTrie */
    private static Node buildTrie(Map<Character, Integer> table) {
        MinPQ<Node> pq = new MinPQ<>();
        for (char ch : table.keySet()) {
            Node trie = new Node(ch, table.get(ch), null, null); // create Node for each of the characters
            pq.insert(trie);
        }
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin(); // the root node
    }

    /* nested Trie Node class */
    private static class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int freq;
        private final Node left;
        private final Node right;
        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        /* check if the node is a leaf node */
        public boolean isLeaf() {
            return (left == null) && (right == null);
        }
        /* compare nodes based on their frequency attribute */
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /* Find the longest prefix that matches given BitSequence, useful when decoding based on a trie */
    public Match longestPrefixMatch(BitSequence query) {
        Node cur = root;
        BitSequence bs = new BitSequence();

        // use bits to walk down the trie
        for (int i = 0; i < query.length(); i += 1) {
            if (cur.isLeaf()) {
                break; // meaning we reached the leaf node that is the longest match in this trie
            }
            if (query.bitAt(i) == 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            bs.append(query.bitAt(i));
        }
        return new Match(bs, cur.ch); // meaning that cur.ch character can be represented by BitSequence bs
    }

    /* Lookup Table for Encoding, the table is based on the Binary Trie */
    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> lookup = new HashMap<>();
        BitSequence bs = new BitSequence();
        buildTable(lookup, root, bs);
        return lookup;
    }

    private static void buildTable(Map<Character, BitSequence> map, Node x, BitSequence bs) {
        if (! x.isLeaf()) {
            buildTable(map, x.left, bs.append(0));
            buildTable(map, x.right, bs.append(1));
        } else {
            map.put(x.ch, bs); // when x is a leaf node
        }
    }
}
