package tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/* implementation of Binary Tree: Generic type */
public class BinaryTreeG<T> {

    /* root of Binary Tree */
    private Node<T> root;

    /* nested class: Node<T> */
    protected static class Node<T> {
        T data;
        int size;
        Node<T> left;
        Node<T> right;

        public Node(T data, int size) {
            this.data = data;
            this.size = size;
            this.left = null;
            this.right = null;
        }

        public Node() {
        }

        public T getData() {
            return data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }

    /* constructors */
    public BinaryTreeG(T data) {
        root = new Node<>(data, 1);
    }

    public BinaryTreeG() {
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    /* return number of nodes in the tree */
    public int size() {
        return size(root);
    }

    /* return size of a given node */
    private int size(Node<T> x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.left) + size(x.right);
    }

    /* return max depth of the tree */
    public int depth() {
        return depth(root);
    }

    /* return max depth of a given node */
    private int depth(Node<T> x) {
        if (x == null) {
            return 0;
        }
        return 1 + Math.max(depth(x.left), depth(x.right));
    }

    /* level order traversal */
    public Iterable<T> levelOrder() {
        return levelOrder(root);
    }

    private Iterable<T> levelOrder(Node<T> x) {
        if (x == null) {
            return null;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        Queue<T> dataQ = new LinkedList<>();
        queue.add(x);

        while (! queue.isEmpty()) {
            Node<T> cur = queue.poll();
            if (cur == null) {
                continue;
            }
            dataQ.add(cur.data);
            queue.add(cur.left);
            queue.add(cur.right);
        }
        return dataQ;
    }

    /* print out tree elements in level order */
    public void printTree() {
        for (T t : levelOrder()) {
            System.out.print(t.toString() + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTreeG<Integer> treeInt = new BinaryTreeG<>(6);
        treeInt.root.left = new Node<>(4, 1);
        treeInt.root.right = new Node<>(9, 1);
        treeInt.root.left.left = new Node<>(2, 1);
        treeInt.root.left.right = new Node<>(5, 1);
        treeInt.root.left.left.left = new Node<>(1, 1);
        treeInt.root.left.left.right = new Node<>(3, 1);
        treeInt.root.right.left = new Node<>(7, 1);
        treeInt.root.right.right = new Node<>(10, 1);
        treeInt.root.right.left.right = new Node<>(8, 1);
        treeInt.printTree();
        System.out.println();
        System.out.println("Total size of this tree is " + treeInt.size());
        System.out.println("The max depth of this tree is " + treeInt.depth());

        System.out.println("----------------DFS PreOrder Traversal----------------");
        System.out.println("Conduct a PreOrder Traversal using recursion:");
        System.out.println(DFSTraversal.traverseRecursion(treeInt, TreeTraversalOrder.PRE_ORDER));
        System.out.println("Conduct a PreOrder Traversal using iteration:");
        System.out.println(DFSTraversal.traverseIteration(treeInt, TreeTraversalOrder.PRE_ORDER));
        System.out.println("Conduct a PreOrder Morris Traversal:");
        System.out.println(DFSTraversal.morrisTraversal(treeInt, TreeTraversalOrder.PRE_ORDER));

        BinaryTreeG<String> treeStr = new BinaryTreeG<>("root");
        treeStr.root.left = new Node<>("this", 1);
        treeStr.root.right = new Node<>("is", 1);
        treeStr.root.left.left = new Node<>("a", 1);
        treeStr.root.left.right = new Node<>("binary", 1);
        treeStr.root.left.left.left = new Node<>("tree", 1);
        treeStr.root.left.left.right = new Node<>("of", 1);
        treeStr.root.left.left.left.left = new Node<>("type", 1);
        treeStr.root.left.left.left.right = new Node<>("string", 1);
        treeStr.printTree();
        System.out.println();
        System.out.println("Total size of this tree is " + treeStr.size());
        System.out.println("The max depth of this tree is " + treeStr.depth());

        System.out.println("----------------BFS Level Order Traversal----------------");
        System.out.println("Conduct a bottom-up level order traversal:");
        System.out.println(LevelOrderTraversal.levelOrder(treeStr, TreeTraversalOrder.LEVEL_ORDER_BOTTOM_UP));
        System.out.println("Conduct a top-down level order traversal:");
        System.out.println(LevelOrderTraversal.levelOrder(treeStr, TreeTraversalOrder.LEVEL_ORDER_TOP_DOWN));

        System.out.println("Conduct a DFS-based level order traversal:");
        System.out.println(LevelOrderTraversal.levelOrderWithDFS(treeStr));

        System.out.println("----------------DFS PreOrder Traversal----------------");
        System.out.println("Conduct a PreOrder Traversal using recursion:");
        System.out.println(DFSTraversal.traverseRecursion(treeStr, TreeTraversalOrder.PRE_ORDER));
        System.out.println("Conduct a PreOrder Traversal using iteration:");
        System.out.println(DFSTraversal.traverseIteration(treeStr, TreeTraversalOrder.PRE_ORDER));
        System.out.println("Conduct a PreOrder Morris Traversal:");
        System.out.println(DFSTraversal.morrisTraversal(treeStr, TreeTraversalOrder.PRE_ORDER));

        System.out.println("----------------DFS InOrder Traversal----------------");
        System.out.println("Conduct an InOrder Traversal using recursion:");
        System.out.println(DFSTraversal.traverseRecursion(treeStr, TreeTraversalOrder.IN_ORDER));
        System.out.println("Conduct an InOrder Traversal using iteration:");
        System.out.println(DFSTraversal.traverseIteration(treeStr, TreeTraversalOrder.IN_ORDER));
        System.out.println("Conduct an InOrder Morris Traversal:");
        System.out.println(DFSTraversal.morrisTraversal(treeStr, TreeTraversalOrder.IN_ORDER));

        System.out.println("----------------DFS PostOrder Traversal----------------");
        System.out.println("Conduct a PostOrder Traversal using recursion:");
        System.out.println(DFSTraversal.traverseRecursion(treeStr, TreeTraversalOrder.POST_ORDER));
        System.out.println("Conduct a PostOrder Traversal using iteration:");
        System.out.println(DFSTraversal.traverseIteration(treeStr, TreeTraversalOrder.POST_ORDER));
        System.out.println("Conduct a PostOrder Morris Traversal:");
        System.out.println(DFSTraversal.morrisTraversal(treeStr, TreeTraversalOrder.POST_ORDER));
    }

}
