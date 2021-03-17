package tree.binarytree;

import java.util.*;

/* DFS Tree Traversals,
* including -- inorder
*           -- preorder
*           -- postorder traversals.
* Recursive and non-recursive solutions provided.
* Stack data structure is preferred by its non-recursive solutions. */
public class DFSTraversal {

    /* return traversal result for a given traversal order using recursion! */
    public static <T> List<T> traverseRecursion(BinaryTreeG<T> tree, TreeTraversalOrder order) {
        switch (order) {
            case IN_ORDER:
                return inOrderTraversalRe(tree);
            case PRE_ORDER:
                return preOrderTraversalRe(tree);
            case POST_ORDER:
                return postOrderTraversalRe(tree);
            default:
                return null;
        }
    }

    /* PreOrder Traversal: root - left - right */
    public static <T> List<T> preOrderTraversalRe(BinaryTreeG<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        List<T> res = new LinkedList<>();
        preOrderHelper(tree.getRoot(), res);
        return res;
    }
    private static <T> void preOrderHelper(BinaryTreeG.Node<T> node, List<T> res) {
        if (node == null) {
            return;
        }
        res.add(node.getData());
        preOrderHelper(node.getLeft(), res); // will conduct preorder traversal on current node's left subtree, if not null
        preOrderHelper(node.getRight(), res); // will conduct preorder traversal on current node's right subtree, if not null
    }

    /* InOrder Traversal: left - root - right */
    public static <T> List<T> inOrderTraversalRe(BinaryTreeG<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        List<T> res = new LinkedList<>();
        inOrderHelper(tree.getRoot(), res);
        return res;
    }
    private static <T> void inOrderHelper(BinaryTreeG.Node<T> node, List<T> res) {
        if (node == null) {
            return;
        }
        inOrderHelper(node.getLeft(), res);
        res.add(node.getData());
        inOrderHelper(node.getRight(), res);
    }

    /* PostOrder Traversal: left - right - root */
    public static <T> List<T> postOrderTraversalRe(BinaryTreeG<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        List<T> res = new LinkedList<>();
        postOrderHelper(tree.getRoot(), res);
        return res;
    }
    private static <T> void postOrderHelper(BinaryTreeG.Node<T> node, List<T> res) {
        if (node == null) {
            return;
        }
        postOrderHelper(node.getLeft(), res);
        postOrderHelper(node.getRight(), res);
        res.add(node.getData());
    }

    /* return traversal result for a given traversal order using iteration!
    * Stack is the preferred data structure. */
    public static <T> List<T> traverseIteration(BinaryTreeG<T> tree, TreeTraversalOrder order) {
        switch (order) {
            case IN_ORDER:
                return inOrderTraversalIt(tree);
            case PRE_ORDER:
                return preOrderTraversalIt(tree);
            case POST_ORDER:
                return postOrderTraversalIt(tree);
            default:
                return null;
        }
    }

    /* Non-recursive PreOrder Traversal: root - left - right */
    public static <T> List<T> preOrderTraversalIt(BinaryTreeG<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        List<T> res = new LinkedList<>();
        Deque<BinaryTreeG.Node<T>> stack = new LinkedList<>();
        BinaryTreeG.Node<T> root = tree.getRoot();

        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeG.Node<T> node = stack.poll();
            res.add(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
        return res;
    }

    /* Non-recursive InOrder Traversal: left - root - right */
    public static <T> List<T> inOrderTraversalIt(BinaryTreeG<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        List<T> res = new LinkedList<>();
        Deque<BinaryTreeG.Node<T>> stack = new LinkedList<>();
        BinaryTreeG.Node<T> root = tree.getRoot();

        while (root != null || !stack.isEmpty()) {
            /* first, add all left subtree nodes into stack.
            * when root == null, we have reached the left most leaf node of the tree. */
            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                /* when root == null, we have traversed all left subtree nodes. */
                root = stack.poll(); // poll out the leftmost node at the top of the stack
                res.add(root.getData()); // add its data to res
                /* current root node is the leftmost node we pushed into stack before,
                * we then check its right subtree by referencing it to the right subtree node.
                * if the right subtree is null, we enter this 'else' condition again;
                * if not null, we will push root into stack (record its role as a root node),
                * and check its left subtree nodes. */
                root = root.getRight();
            }
        }
        return res;
    }

    /* Non-recursive PostOrder Traversal: left - right - root,
    * very similar to PreOrder Traversal except the order of adding items into list. */
    public static <T> List<T> postOrderTraversalIt(BinaryTreeG<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return null;
        }
        List<T> res = new LinkedList<>();
        Deque<BinaryTreeG.Node<T>> stack = new LinkedList<>();
        BinaryTreeG.Node<T> root = tree.getRoot();

        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeG.Node<T> node = stack.poll();
            res.add(0, node.getData());
            /* we need to follow a 'left then right' order to push nodes into stack,
            * because we are adding values into res backwards. */
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
        }
        return res;
    }

}
