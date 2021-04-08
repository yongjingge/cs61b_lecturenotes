package lecture28;

import lecture27.Digraph;
import java.util.ArrayDeque;
import java.util.Queue;

/* Compute preorder, postorder, and reverse postorder for a digraph.
* Run in O(E + V) time.
* This implementation uses depth-first search.
* @source https://algs4.cs.princeton.edu/42digraph/DepthFirstOrder.java.html
*  */
public class DepthFirstOrder {

    private boolean[] marked; // marked[v] = has vertex v been marked in dfs?
    private int[] pre; // pre[v] = index of vertex v in dfs preorder
    private int[] post; // post[v] = index of vertex v in dfs postorder
    private Queue<Integer> preorder; // vertices in preorder
    private Queue<Integer> postorder; // vertices in postorder
    private int preCounter; // counter for preorder numbering, should be 0 - (V-1)
    private int postCounter; // counter for postorder numbering, should be 0 - (V-1)

    /* determines a depth-first order for the graph */
    public DepthFirstOrder(Digraph G) {
        int V = G.V();
        marked = new boolean[V];
        pre = new int[V];
        post = new int[V];
        preorder = new ArrayDeque<>();
        postorder = new ArrayDeque<>();
        for (int i = 0; i < V; i += 1) {
            if (! marked[i]) {
                dfs(G, i);
            }
        }
        assert check();
    }

    /* run DFS and compute preorder/postorder */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        // preorder: put vertex on a queue BEFORE the dfs calls
        pre[v] = preCounter++;
        preorder.add(v);
        for (int w : G.adj(v)) {
            if (! marked[w]) {
                dfs(G, w);
            }
        }
        // postorder: put vertex on a queue AFTER the dfs calls
        post[v] = postCounter++;
        postorder.add(v);
    }

    /* returns the index of vertex v in preorder */
    public int getPreIndex(int v) {
        validateVertex(v);
        return pre[v];
    }

    /* returns the index of vertex v in postorder */
    public int getPostIndex(int v) {
        validateVertex(v);
        return post[v];
    }

    /* returns vertices in preorder */
    public Iterable<Integer> getPreorder() {
        return preorder;
    }

    /* returns vertices in postorder */
    public Iterable<Integer> getPostorder() {
        return postorder;
    }

    /* returns vertices in reverse postorder using STACK */
    public Iterable<Integer> getReversePostorder() {
        ArrayDeque<Integer> reverse = new ArrayDeque<>();
        for (int v : getPostorder()) {
            reverse.push(v);
        }
        return reverse;
    }

    /* check that pre() and post() are consistent with pre(v) and post(v) */
    private boolean check() {
        // check that getPreIndex(v) is consistent with getPreorder()
        int r = 0;
        for (int v : getPreorder()) {
            if (getPreIndex(v) != r) {
                // indices of preorder start from 0
                return false;
            }
            r += 1;
        }

        // check that getPostIndex(v) is consisitent with getPostorder()
        r = 0;
        for (int v : getPostorder()) {
            if (getPostIndex(v) != r) {
                return false;
            }
            r += 1;
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

}
