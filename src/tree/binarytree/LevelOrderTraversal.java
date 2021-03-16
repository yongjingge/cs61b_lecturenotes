package tree.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* Level Order Traversal of a given binary tree.
* Following BFS(Breadth First Search),
* normally using Queue as its preferred data structure.
* Different solutions provided. */
public class LevelOrderTraversal {

    /* Queue-based level order traversal,
    * in a given traversal order. */
    public static <T> List<List<T>> levelOrder(BinaryTreeG<T> tree, TreeTraversalOrder order) {
        if (tree.getRoot() == null || tree == null) {
            return null;
        }
        Queue<BinaryTreeG.Node> queue = new LinkedList<>();
        List<List<T>> res = new LinkedList<>();
        queue.add(tree.getRoot());

        while (! queue.isEmpty()) {
            int level = queue.size();
            List<T> sub = new LinkedList<>();
            for (int i = 0; i < level; i += 1) {
                BinaryTreeG.Node<T> cur = queue.poll();
                sub.add(cur.getData());
                if (cur.getLeft() != null) {
                    queue.add(cur.getLeft());
                }
                if (cur.getRight() != null) {
                    queue.add(cur.getRight());
                }
            }

            switch (order) {
                case LEVEL_ORDER_BOTTOM_UP:
                    res.add(0, sub);
                    break;
                case LEVEL_ORDER_TOP_DOWN:
                    res.add(sub);
                    break;
            }
        }
        return res;
    }

    public static <T> List<List<T>> levelOrderWithDFS(BinaryTreeG<T> tree) {
        List<List<T>> res = new LinkedList<>();
        BinaryTreeG.Node<T> root = tree.getRoot();
        levelHelper(res, root, 0);
        return res;
    }
    private static <T> void levelHelper(List<List<T>> res, BinaryTreeG.Node<T> node, int depth) {
        if (node == null) {
            return;
        }
        /* if this is the first node we encountered at current level,
        * we create a new list to store data. */
        if (depth == res.size()) {
            res.add(new LinkedList<>());
        }

        /* initially add root.data to res, depth is set to 0 */
        res.get(depth).add(node.getData());

        /* implement pre-order traversal to achieve level order */
        levelHelper(res, node.getLeft(), depth + 1);
        levelHelper(res, node.getRight(), depth + 1);
    }
}
