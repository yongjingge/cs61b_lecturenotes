package lecture27;

import edu.princeton.cs.algs4.Bag;

/* An adjacency-lists representation of Graph(directed!)
* which will maintain a vertex-indexed array of lists of vertices
* connected by an edge to each vertex.
* @source https://algs4.cs.princeton.edu/42digraph/Digraph.java.html
*  */
public class Digraph {

    private int V; // number of vertices in this graph
    private int E; // number of edges in this graph
    private Bag<Integer>[] adj; // adj[v] = adjacency list for vertex v
    private int[] indegree; // indegree[v] = indegree of vertex v;

    /* Initializes an empty graph with V vertices */
    public Digraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative.");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // every adj[i] is a Bag of Integers
        indegree = new int[V];
        for (int v = 0; v < V; v += 1) {
            adj[v] = new Bag<Integer>();
        }
    }

    /* Returns the number of vertices in this graph */
    public int V() {
        return V;
    }

    /* Returns the number of edges in this graph */
    public int E() {
        return E;
    }

    /* check vertex index validation */
    private void validateVertex(int v) {
        if (v < 0 || v > V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid.");
        }
    }

    /* Adds DIRECTED edge v->w to this graph */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w); // an edge from v to w is added
        indegree[w] += 1; // vertex w's degree should plus 1
        E += 1;
    }

    /* Returns the vertices adjacent from given vertex in this graph */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v]; // will return a Bag of adjacents
    }

    /* Returns the number of directed edges incident FROM given vertex */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /* Returns the number of directed edges incident TO given vertex */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /* Returns the reverse of the graph */
    public Digraph reverse() {
        Digraph reverseG = new Digraph(V);
        for (int v = 0; v < V; v += 1) {
            for (int w : adj(v)) {
                reverseG.addEdge(w, v); // add DIRECTED edge w->v to the graph, so it is reversed!
            }
        }
        return reverseG;
    }
}
