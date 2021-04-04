package lecture27;

/* This DFS class represents a data type for determining
* the vertices reachable from a given Source Vertex (single-source reachability) or a set of source vertices (multiple-source reachability).
*
* This version does not find the paths.
* @source https://algs4.cs.princeton.edu/42digraph/DirectedDFS.java.html
*  */
public class DirectedDFS {

    private boolean[] marked; // marked[v] = true if vertex 'v' is reachable from source vertex 's'
    private int count; // number of vertices reachable from source vertex 's'

    /* Given a graph 'G', this class will compute the vertices in the graph
    * that are reachable from source vertex 's'.
    * @param G the given graph
    * @param s the source vertex
    *  */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()]; // marked.length will be the number of vertices in given graph
        validateVertex(s);
        dfs(G, s);
    }

    /* Computes the vertices in given graph 'G' that are
    * connect to any of the source vertices 'sources'
    * @param G the given graph
    * @param sources the source vertices, it is Iterable<Integer> in this case
    *  */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        validateVertices(sources);
        for (int v : sources) {
            if (! marked[v]) {
                dfs(G, v);
            }
        }
    }

    /* */

    /* dfs(G, v)
    * given vertex 'v' must be reachable from the original source vertex,
    * so count += 1
    *  */
    private void dfs(Digraph G, int v) {
        cout += 1;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (! marked[w]) {
                dfs(G, w);
            }
        }
    }

    /* Is it reachable from any source vertex to target vertex v? */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /* Returns the number of vertices reachable from source vertex */
    public int count() {
        return count;
    }

    /* Checks if the vertex index is valid */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v > V) {
            throw new IllegalArgumentException("vertex " + v + " is not valid!");
        }
    }

    /* Checks if any of the given vertices is invalid */
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        int V = marked.length;
        int countOfVertices = 0;
        for (Integer v : vertices) {
            countOfVertices += 1;
            if (v == null) {
                throw new IllegalArgumentException("vertex is null");
            }
            validateVertex(v);
        }
        if (countOfVertices == 0) {
            throw new IllegalArgumentException("zero vertices");
        }
    }
}
