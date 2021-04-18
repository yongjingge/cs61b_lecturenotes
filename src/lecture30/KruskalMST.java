package lecture30;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

import java.util.Arrays;

/* Computes a minimum spanning tree in an edge-weighted graph.
* The edge weights can be positive, zero, or negative and need not to be distinct.
* If the given graph is not connected, it will compute a minimum spanning forest,
* which is the union of minimum spanning trees in each connected component.
* @source https://algs4.cs.princeton.edu/43mst/KruskalMST.java.html
*  */
public class KruskalMST {

    private static final double FLOATING_POINT_EPSILON = 1E-12;
    private double weight; // weight of MST
    private Queue<Edge> mst = new Queue<>(); // edges in MST

    /* compute a minimum spanning tree or forest */
    public KruskalMST(EdgeWeightedGraph G) {
        int V = G.V();
        int E = G.E();

        // create array of Edges, sorted by weight
        Edge[] edges = new Edge[E];
        int i = 0;
        for (Edge e : G.edges()) {
            edges[i] = e;
            i += 1;
        }
        Arrays.sort(edges); // sort weighted edges using Array

        // run Greedy Algorithm
        UF uf = new UF(V);
        for (i = 0; i < E && mst.size() < (V - 1); i += 1) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);
            // check if edge v-w forms a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }

        // check optimality conditions
        assert check(G);
    }

    /* return the edges in a minimum spanning tree or forest */
    public Iterable<Edge> getMSTEdges() {
        return mst;
    }

    /* return the sum of the edge weights in a minimum spanning tree or forest */
    public double getTotalWeight() {
        return weight;
    }

    /* check optimality conditions */
    private boolean check(EdgeWeightedGraph G) {
        // check total weight
        double total = 0.0;
        for (Edge e : getMSTEdges()) {
            total += e.weight();
        }
        if (Math.abs(total - getTotalWeight()) > FLOATING_POINT_EPSILON) {
            return false;
        }

        // check if it is acyclic
        UF uf = new UF(G.V());
        for (Edge e : getMSTEdges()) {
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                return false;
            }
            uf.union(v, w);
        }

        // check if it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                return false;
            }
        }

        // check its optimality conditions
        for (Edge e : getMSTEdges()) {
            // get all edges in MST except e
            uf = new UF(G.V());
            for (Edge f : getMSTEdges()) {
                int v = f.either();
                int w = f.other(v);
                if (f != e) {
                    uf.union(v, w);
                }
                // this will leave edge e's vertices v and w unconnected in uf
            }

            for (Edge f : G.edges()) {
                int v = f.either();
                int w = f.other(v);
                if (uf.find(v) != uf.find(w)) {
                    // we have found edge e (all edges in MST except e have connected their vertices in uf)
                    if (f.weight() < e.weight()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
