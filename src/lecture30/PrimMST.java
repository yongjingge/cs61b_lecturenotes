package lecture30;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.UF;

import java.util.ArrayDeque;
import java.util.Queue;

/* Prim's Algorithm without comments */
public class PrimMST {

    private static final double FLOATING_POINT_EPSILON = 1E-12;
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        int V = G.V();
        edgeTo = new Edge[V];
        distTo = new double[V];
        marked = new boolean[V];
        pq = new IndexMinPQ<>(V);

        for (int i = 0; i < V; i += 1) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 0; i < V; i += 1) {
            if (! marked[i]) {
                prim(G, i);
            }
        }

        assert check(G);
    }

    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (! pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
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

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new ArrayDeque<>();
        for (int i = 0; i < distTo.length; i += 1) {
            Edge e = edgeTo[i];
            if (e != null) {
                mst.add(e);
            }
        }
        return mst;
    }

    public double weight() {
        double res = 0.0;
        for (Edge e : edges()) {
            res += e.weight();
        }
        return res;
    }

    private boolean check(EdgeWeightedGraph G) {
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            return false;
        }

        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                return false;
            }
            uf.union(v, w);
        }

        for (Edge e : G.edges()) {
            int v = e.either();
            int w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                return false;
            }
        }

        for (Edge e : edges()) {
            uf = new UF(G.V());
            for (Edge f : edges()) {
                int v = f.either();
                int w = f.other(v);
                if (f != e) {
                    uf.union(v, w);
                }
            }

            for (Edge f : G.edges()) {
                int v = f.either();
                int w = f.other(v);
                if (uf.find(v) != uf.find(w)) {
                    if (f.weight() < e.weight()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
