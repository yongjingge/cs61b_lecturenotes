package lecture30;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import lecture20.WeightedQuickUnionWithPathCompression;

import java.util.ArrayList;
import java.util.List;

/* Computes a minimum spanning tree in an edge-weighted graph.
* This implementation uses a special Priority Queue to sort weighted edges.
* Its overall runtime is O(E logE).
*
* If we use a pre-sorted list of edges instead of a priority queue to store all edges
* in an increasing order of weight, the overall runtime will be better since we don't
* need to do insertion and deletion operations. The overall runtime can be O(E log*V).
*  */
public class Kruskal {

    private double weight; // weight of MST
    private List<Edge> mst = new ArrayList<>(); // edges in MST

    /* constructor */
    public Kruskal(EdgeWeightedGraph G) {
        // using a special PQ to store all edges in an increasing order of weight
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        // using a weighted quick union to check if there is a cycle
        int V = G.V();
        WeightedQuickUnionWithPathCompression uf = new WeightedQuickUnionWithPathCompression(V);
        while (! pq.isEmpty() && mst.size() < (V - 1)) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (! uf.isConnected(v, w)) {
                mst.add(e);
                uf.connect(v, w);
                weight += e.weight();
            }
        }
    }

    /* returns the edges in a minimum spanning tree or forest */
    public Iterable<Edge> getMSTEdges() {
        return mst;
    }

    /* returns the sum of the edge weights in a MST/Forest */
    public double getTotalWeight() {
        return weight;
    }

    /* check method: see KruskalMST */
}
