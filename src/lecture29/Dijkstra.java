package lecture29;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.ArrayDeque;
import java.util.Deque;

/* Dijkstra's Algorithm:
* Solving the single-source shortest paths problem in edge-weighted digraphs.
* Computes the SPT (shortest path tree).
* Assumes all weights are non-negative.
* Edges without weight will just be Breadth First Search.
* @source https://algs4.cs.princeton.edu/44sp/DijkstraSP.java.html
* */
public class Dijkstra {

    private double[] distTo; // distTo[v] = distance of shortest s->v path, v is target vertex
    private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq; // priority queue of vertices

    /* Constructor:
    * computes a shortest-paths tree from the source vertex [s]
    * to every other vertex in the edge-weighted digraph G */
    public Dijkstra(EdgeWeightedDigraph G, int s) {
        // check if G has negative-weighted edges
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            }
        }
        int V = G.V(); // get the number of vertices
        distTo = new double[V];
        edgeTo = new DirectedEdge[V];

        // check if 's' is valid
        validateVertex(s);

        // set every items in distTo[] to infinity except the starting vertex 's'
        setDistancesToInfinityExceptS(s);

        // add source vertex [s] into pq
        pq = new IndexMinPQ<>(V);
        pq.insert(s, distTo[s]); // pq insert source vertex, distance from s to s which is 0.0
        while (! pq.isEmpty()) {
            int v = pq.delMin(); // remove vertex with smallest distance from source
            for (DirectedEdge e : G.adj(v)) {
                relax(e); // relax all edges pointing from vertex [v], which is the removed vertex
            }
        }

        // check optimality conditions
        assert check(G, s);
    }

    /* relax edges pointing from edge 'e' and update pq if changed */
    private void relax(DirectedEdge e) {
        // a directed edge v->w
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight(); // if we find better distance, we update the distance from source vertex to vertex [w]
            edgeTo[w] = e; // add the path to SPT if vertex [w] is still active
            // we will update priority of vertex [w] if it already in the pq,
            // or we add it into the pq
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    /* return the length of a shortest path from source vertex [s] to target vertex [v] */
    public double getDistance(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /* return true if there is a path from source vertex [s] to target vertex [v] */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /* return a shortest path from source vertex [s] to target vertex [v] */
    public Iterable<DirectedEdge> getPathTo(int v) {
        validateVertex(v);
        if (! hasPathTo(v)) {
            return null;
        }
        Deque<DirectedEdge> path = new ArrayDeque<>();
        // DirectedEdge e = w->v, e.from = w; e.to = v
        // adding 'e' into the stack until we reach the source vertex
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    /* check if index of a given vertex is valid */
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    /* check optimality conditions
    * (i) for all edges e: distTo[e.to()] <= distTo[e.from()] + e.weight()
    * (ii) for all edges e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight() */
    private boolean check(EdgeWeightedDigraph G, int s) {
        // check that edge weights are non-negative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.out.println("Negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        // for source vertex, distTo[s] should == 0, edgeTo[s] should be null
        if ((distTo[s] != 0.0) || (edgeTo[s] != null)) {
            System.out.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int i = 0; i < G.V(); i += 1) {
            if (i == s) {
                continue;
            }
            // when we traverse to other vertices but source, we expect
            // edgeTo[i] != null || distTo[i] == Double.POSITIVE_INFINITY
            if (edgeTo[i] == null && distTo[i] != Double.POSITIVE_INFINITY) {
                System.out.println("distTo[i] and edgeTo[i] inconsistent");
                return false;
            }
        }

        // check that all edges e: v->w, not necessarily on the path, should satisfy
        // distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v += 1) {
            for (DirectedEdge e : G.adj(v)) {

                // relax each of vertex v's outgoing edges, e.g. v->w, it is already optimized
                // should satisfy distTo[w] <= distTo[v] + edge.weight()
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight()) {
                    System.out.println("edge " + e + " is not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e: v->w on the Shortest Paths Tree, should satisfy
        // distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w += 1) {
            if (edgeTo[w] == null) {
                // w is the source vertex
                continue;
            }
            // to get the edge on the SPT, we should use edgeTo[]
            DirectedEdge e = edgeTo[w]; // get edge v->w on the SPT
            int v = e.from();
            if (w != e.to()) {
                return false;
            }
            if (distTo[v] + e.weight() != distTo[w]) {
                return false;
            }
        }
        return true;
    }

    /* helper method to set every items in distTo[] to infinity except the source vertex */
    private void setDistancesToInfinityExceptS(int s) {
        for (int i = 0; i < distTo.length; i += 1) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
    }

}
