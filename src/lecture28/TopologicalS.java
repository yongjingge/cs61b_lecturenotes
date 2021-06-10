package lecture28;

import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayDeque;

/* Recursive implementation of Topological ordering of a DAG (directed acyclic graph).
 * This implementation uses depth-first search.
 * @source lecture28 */
public class TopologicalS {

    private boolean[] marked;
    private ArrayDeque<Integer> reversePost;

    public TopologicalS(Digraph G) {
        marked = new boolean[G.V()];
        reversePost = new ArrayDeque<>();
        for (int i = 0; i < G.V(); i += 1) {
            if (! marked[i]) {
                dfs(G, i);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (! marked[w]) {
                dfs(G, w);
            }
        }
        reversePost.push(v);
    }

    public Iterable<Integer> getOrder() {
        return reversePost;
    }
}
