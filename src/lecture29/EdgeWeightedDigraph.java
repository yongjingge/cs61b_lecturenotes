package lecture29;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdRandom;

/* Represent a edge-weighted digraph of vertices named
* 0 through V-1 (V: number of vertices), where
* each directed edge is of type 'DirectedEdge' and has a real-value weight.
* This implementation uses an adjacency-lists representation, which is
* a vertex-indexed array of Bag objects.
* @source https://algs4.cs.princeton.edu/44sp/EdgeWeightedDigraph.java.html
*  */
public class EdgeWeightedDigraph {

    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<DirectedEdge>[] adj; // adj[v] = adjacency list of vertex v, v's outgoing edges
    private int[] indegree; // indegree[v] = indegree of vertex v

    /* initialize an empty edge-weighted digraph with V vertices and 0 edges */
    public EdgeWeightedDigraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        }
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v += 1) {
            adj[v] = new Bag<>();
        }
    }

    /* initializes a random edge-weighted digraph with V vertices and E edges */
    public EdgeWeightedDigraph(int V, int E) {
        this(V); // initializes an empty edge-weighted digraph with V vertices and 0 edges
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges in a Digraph must be non-negative");
        }
        // randomly generates edges from v to w (v->w)
        for (int i = 0; i < E; i += 1) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100); // generates random weight for each edge
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    /* adds the directed edge to this digraph */
    public void addEdge(DirectedEdge e) {
        // edge: v -> w
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e); // v's outgoing edges
        indegree[w] += 1;
        E += 1;
    }

    /* returns the number of vertices in this graph */
    public int V() {
        return V;
    }

    /* returns the number of edges in this graph */
    public int E() {
        return E;
    }

    /* checks if the vertex's index is valid */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    /* returns the directed edges incident from vertex [v] */
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v]; // return all edges that point from vertex v
    }

    /* returns the number of directed edges incident FROM vertex [v] */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /* returns the number of directed edges incident TO vertex [v] */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /* returns all directed edges in this edge-weighted digraph,
    * to iterate over the edges, use 'foreach' notation */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edgesList = new Bag<>();
        for (int v = 0; v < V; v += 1) {
            // for each directed edges in adj(v)
            for (DirectedEdge e : adj(v)) {
                edgesList.add(e);
            }
        }
        return edgesList;
    }

}
