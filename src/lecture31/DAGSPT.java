package lecture31;


import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Topological;

import java.util.ArrayDeque;
import java.util.Deque;

/* The DAGSPT algorithm demonstrates the implementation of
* Dynamic Programming
* This algorithm works only on DAGs.
* It can do slightly better than the Dijkstra's Algorithm,
* and it can deal with negative/zero weighted edges.
* This implementation uses a Topological-sort based algorithm.
*
* @source https://algs4.cs.princeton.edu/44sp/AcyclicSP.java.html
*  */
public class DAGSPT {

    private double[] distTo; // distTo[v] = distance of shortest s-v path
    private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s-v path

    /* constructor: computes a shortest path tree from given source vertex
    * to every other vertex in the DAG */
    public DAGSPT(EdgeWeightedDigraph G, int s) {
        int V = G.V();
        distTo = new double[V];
        edgeTo = new DirectedEdge[V];
        validateVertex(s);

        // set all vertices' distTo to infinite except the starting vertex
        setDistTo(s);

        // visit vertices in Topological order, and relax their outgoing edges
        Topological torder = new Topological(G);
        if (! torder.hasOrder()) {
            throw new IllegalArgumentException("Not a DAG");
        }
        for (int v : torder.order()) { // visit vertices in topological order
            for (DirectedEdge e : G.adj(v)) { // relax its outgoing edges
                relax(e);
            }
        }
    }

    // relax edges
    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    // return the length of a shortest s-v path
    public double getDistance(int v) {
        validateVertex(v);
        return distTo[v];
    }

    // return true if there is a path from source vertex to given target vertex [v]
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // return a shortest path from source vertex to given target vertex [v]
    public Iterable<DirectedEdge> getPathTo(int v) {
        validateVertex(v);
        if (! hasPathTo(v)) {
            return null;
        }
        Deque<DirectedEdge> path = new ArrayDeque<>();
        // traverse backwards
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    // set all vertices' distTo to infinite except the starting vertex
    private void setDistTo(int s) {
        int V = distTo.length;
        for (int i = 0; i < V; i += 1) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
    }

    // check if the vertex's index is valid
    private void validateVertex(int s) {
        int V = distTo.length;
        if (s < 0 || s >= V) {
            throw new IllegalArgumentException("vertex " + s + "is invalid");
        }
    }
}
