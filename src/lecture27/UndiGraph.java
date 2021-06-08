package lecture27;

import java.util.ArrayList;
import java.util.List;

/* from lecture
* Bare-Bones Undirected Graph Implementation */
public class UndiGraph {

    private final int V;
    private List<Integer>[] adj;

    public UndiGraph(int v) {
        V = v;
        adj = (List<Integer>[]) new ArrayList[v]; // each adj item is an arraylist
        for (int i = 0; i < v; i += 1) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        // add edge v - w
        adj[v].add(w); // v's adjacent vertices
        adj[w].add(v); // w's adjacent vertices
    }

    public Iterable<Integer> adj(int v) {
        // get vertex v's adjacent vertices (vertices of v's outgoing edges)
        return adj[v];
    }
}
