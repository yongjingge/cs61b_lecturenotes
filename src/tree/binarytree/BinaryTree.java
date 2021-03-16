package tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/* implementation of Binary Tree: int type */
public class BinaryTree {

    /* root of Binary Tree */
    private Node root;

    /* nested class: Node */
    private static class Node {
        int key;
        int size;
        Node left;
        Node right;

        public Node(int key, int size) {
            this.key = key;
            this.size = size;
            this.left = null;
            this.right = null;
        }
    }

    /* constructor */
    public BinaryTree(int key) {
        root = new Node(key, 1);
    }

    /* constructor for empty Binary Tree */
    public BinaryTree() {
        root = null;
    }

    /* return number of nodes in the tree */
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.left) + size(x.right);
    }

    /* level order traversal */
    public Iterable<Integer> levelOrder() {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> keys = new LinkedList<>();
        queue.add(root);

        while (! queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur == null) {
                continue;
            }
            keys.add(cur.key);
            queue.add(cur.left);
            queue.add(cur.right);
        }
        return keys;
    }

    public void printTree() {
        levelOrder().forEach(integer -> System.out.print(integer + " "));
//        for (Integer i : levelOrder()) {
//            System.out.println(i.toString());
//        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new Node(2, 1);
        tree.root.right = new Node(3, 1);
        tree.root.left.left = new Node(4, 1);
        tree.root.left.right = new Node(5, 1);
        tree.root.right.left = new Node(6, 1);
        tree.root.right.right = new Node(7, 1);
        tree.printTree();
        System.out.println();
        System.out.println("Total size of this tree is " + tree.size());
    }
}