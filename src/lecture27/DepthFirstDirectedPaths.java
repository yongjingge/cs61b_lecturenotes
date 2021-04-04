package lecture27;

import java.util.ArrayDeque;

/* Single-source directed paths:
* Given a graph G and a source vertex s,
* is there a directed path from s to v?
* If so, find the path.
* @source https://algs4.cs.princeton.edu/42digraph/DepthFirstDirectedPaths.java.html */
public class DepthFirstDirectedPaths {

    private boolean[] marked;
    private int[] edgeTo; // edgeTo[v] = last edge on path from vertex s to vertex v
    private final int s; // source vertex

    /* Computes a directed path from vertex 's' to every other vertex in the graph */
    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        validateVertex(s);
        dfs(G, s);
    }

    /* DFS */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (! marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /* Checks if there is a directed path from vertex 's' to vertex 'v' */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /* Returns a directed path from vertex 's' to vertex 'v' if exists */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (! hasPathTo(v)) {
            return null;
        }
        ArrayDeque<Integer> path = new ArrayDeque<>();
        // traverse backwards from vertex 'v' to its previous edgeTo[v] vertex until it meets vertex 's'
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    /* Checks if the vertex index is valid */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }
}
