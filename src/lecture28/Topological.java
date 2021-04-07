package lecture28;

import lecture27.Digraph;

/* Recursive implementation of Topological ordering of a DAG (directed acyclic graph).
* This implementation uses depth-first search.
* @source https://algs4.cs.princeton.edu/42digraph/Topological.java.html */
public class Topological {

    private Iterable<Integer> order; // stores vertices in a topological order if the graph has one
    private int[] ranks; // ranks[v] = rank of vertex v, representing the index of vertex v in a topological order

    /* Constructor: an operation determines whether the graph has
    * a topological order. */
    public Topological(Digraph G) {
        /* determines if the graph has cycles */
        DirectedCycle finder = new DirectedCycle(G);

        if (! finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            /* DFS Postorder reversed version = topological ordering */
            order = dfs.reversePost();
            ranks = new int[G.V()];
            int i = 0; // ranks' indices start from 0
            for (int v : order) {
                // vertices of topological ordering should be consistent with the direction of edges
                ranks[v] = i ++;
                /*
                * ranks[v] = i;
                * i += 1;
                *  */
            }
        }
    }

    /* returns a topological order if the graph has one,
    * and null otherwise. */
    public Iterable<Integer> getTopologicalOrder() {
        return order;
    }

    /* does the graph have a topological order? */
    public boolean hasOrder() {
        return order != null;
    }

    /* returns the rank of given vertex v in topological order,
    * returns -1 if the graph if NOT a DAG. */
    public int getRank(int v) {
        validateVertex(v);
        if (hasOrder()) {
            return ranks[v];
        }
        return -1;
    }

    private void validateVertex(int v) {
        int V = ranks.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }
}
