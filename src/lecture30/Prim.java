package lecture30;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.UF;

import java.util.ArrayDeque;
import java.util.Queue;

/* Prim's Algorithm:
* Computes a minimum spanning tree in an edge-weighted graph, not necessarily directed!
* The edge weights can be positive, zero, or negative, and need not to be distinct.
* If the graph is not connected, it will compute a minimum spanning forest which is
* the union of minimum spanning trees in each connected components.
* It uses Prim's Algorithm with an index binary heap(IndexMinPQ).
* This implementation is a little different from cs61b's.
* @source https://algs4.cs.princeton.edu/43mst/PrimMST.java.html
*  */
public class Prim {

    private static final double FLOATING_POINT_EPSILON = 1E-12;
    private Edge[] edgeTo; // edgeTo[v] = shortest path(edge) from tree vertex to a non-tree vertex [v]
    private double[] distTo; // distTo[v] = weight of a shortest path from tree vertex to a non-tree vertex [v], namely weight of a crossing edge connects two sides of a cut
    private boolean[] marked; // marked[v] = true if vertex v is on the MST, false otherwise
    private IndexMinPQ<Double> pq; // fringePQ initially inserts all vertices in the order of distance from the tree

    /* computes a minimum spanning tree of an edge-weighted graph,
    * not a directed graph! */
    public Prim(EdgeWeightedGraph G) {
        int V = G.V(); // get number of vertices in the given graph
        edgeTo = new Edge[V];
        distTo = new double[V];
        marked = new boolean[V];
        pq = new IndexMinPQ<>(V);
        // set every items in distTo[] to infinity
        for (int i = 0; i < V; i += 1) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // run from each vertex to find minimum spanning forest (forest in case that the graph is not fully connected)
        // note that this is for minimum spanning FOREST, normally MST does not need a starting vertex
        // if the graph is not fully connected, it means we need multiple MST that start from different vertices
        for (int i = 0; i < V; i += 1) {
            if (! marked[i]) {
                prim(G, i); // run prim on each vertex: remove closest vertex from PQ and relax all edges pointing from that vertex
            }
        }

        // check optimality conditions
        assert check(G);
    }

    /* run Prim's Algorithm in the given graph G, starting from vertex s */
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0; // distance from a tree vertex to a non-tree vertex
        pq.insert(s, distTo[s]);
        while (! pq.isEmpty()) {
            // remove the closest vertex v and relax all its outgoing edges
            // note: scan(G, vertex) will check and update all if vertex's outgoing edges,
            // while pq is not empty, if we delete min from pq, the deleted vertex [v] and its edgeTo[v] will be part of the MST edges
            // and we continue to scan that removed vertex, moving forward until we have V-1 edges in MST.
            int v = pq.delMin();
            scan(G, v);
        }
    }

    /* scan vertex v, namely relax all its outgoing edges and update PQ if needed */
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true; // scan vertex v means that vertex v must be in the MST
        for (Edge e : G.adj(v)) {
            int w = e.other(v); // get the other side of edge e
            if (marked[w]) {
                continue;
            }
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.decreaseKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    /* return edges in MST */
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new ArrayDeque<Edge>();
        for (int v = 0; v < distTo.length; v += 1) {
            Edge e = edgeTo[v]; // start from edgeTo[0]
            if (e != null) {
                mst.add(e);
            }
        }
        return mst;
    }

    /* return the sum of the edge weights in a MST or MSF(forest) */
    public double weight() {
        double res = 0.0;
        for (Edge e : edges()) {
            res += e.weight();
        }
        return res;
    }

    /* check optimality conditions */
    private boolean check(EdgeWeightedGraph G) {
        // check total weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            return false;
        }

        // check if it is acyclic
        UF uf = new UF(G.V()); // use a quick union to check if MST is cyclic
        for (Edge e : edges()) {
            int v = e.either();
            int w = e.other(v); // edge e is v-w
            if (uf.find(v) == uf.find(w)) {
                return false; // meaning that without edge e, vertex v and w are already connected to each other, crossing edges already exist.
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            // get all edges of the graph
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                // meaning vertex v and vertex w is not connected
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {
            // get all MST edges
            uf = new UF(G.V()); // instantiate a new UF
            for (Edge f : edges()) {
                // for all edges in MST except edge e
                int x = f.either();
                int y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }
            // check that edge e is the minimum weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either();
                int y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
