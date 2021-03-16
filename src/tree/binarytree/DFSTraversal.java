package tree.binarytree;

import java.util.LinkedList;
import java.util.List;

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
        preOrderHelper(node.getLeft(), res);
        preOrderHelper(node.getRight(), res);
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
}
