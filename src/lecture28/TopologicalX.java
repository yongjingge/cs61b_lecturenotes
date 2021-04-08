package lecture28;

import lecture27.Digraph;

import java.util.ArrayDeque;
import java.util.Queue;

/* Non-recursive implementation of Topological Ordering of a DAG.
* This implementation uses queue-based algorithm.
* @source https://algs4.cs.princeton.edu/42digraph/TopologicalX.java.html
*  */
public class TopologicalX {

    private Queue<Integer> order; // vertices in topological order
    private int[] ranks; // ranks[v] = rank of vertex v, representing the index of vertex v in a topological order

    /* Constructor: an operation determines whether the graph has
    * a topological order. */
    public TopologicalX(Digraph G) {
        int V = G.V();
        // indegrees of remaining vertices
        int[] indegree = new int[V];
        for (int i = 0; i < V; i += 1) {
            indegree[i] = G.indegree(i);
        }
        // initialize
        ranks = new int[V];
        order = new ArrayDeque<>();
        int count = 0;

        // initialize a new queue to contain all vertices with indegree = 0
        Queue<Integer> queueOfStartingVertices = new ArrayDeque<>();
        for (int i = 0; i < V; i += 1) {
            if (indegree[i] == 0) {
                queueOfStartingVertices.add(i);
            }
        }

        while(! queueOfStartingVertices.isEmpty()) {
            int x = queueOfStartingVertices.remove();
            order.add(x);
            ranks[x] = count++;
            /* ranks[x] = count;
            * count += 1;
            *  */
            for(int w : G.adj(x)) {
                indegree[w] -= 1;
                if (indegree[w] == 0) {
                    queueOfStartingVertices.add(w);
                }
            }
        }

        if (count != V) {
            order = null;
        }
        assert check(G);
    }

    /* returns a topological order if has one */
    public Iterable<Integer> getOrder() {
        return order;
    }

    /* does the graph have a topological order? */
    public boolean hasOrder() {
        return order != null;
    }

    /* returns the index of vertex v in topological order */
    public int getRank(int v) {
        validateVertex(v);
        if (! hasOrder()) {
            return -1;
        }
        return ranks[v];
    }

    /* checks that graph is acyclic */
    private boolean check(Digraph G) {

        if (hasOrder()) {
            // check that ranks are a permutation of 0 to V-1
            int V = G.V();
            boolean[] found = new boolean[V];
            for (int i = 0; i < V; i += 1) {
                found[getRank(i)] = true;
            }
            for (int i = 0; i < V; i += 1) {
                if (! found[i]) {
                    return false; // no vertex with rank i
                }
            }

            // check that ranks provide a valid topological order
            for (int i = 0; i < V; i += 1) {
                for (int w : G.adj(i)) {
                    // directed edge should be i->w
                    // ranks[i] should be smaller than ranks[w] as it occurs first before in topological order.
                    if (getRank(i) > getRank(w)) {
                        return false;
                    }
                }
            }

            // check that getOrder() is consistent with getRank()
            int r = 0;
            for (int v : getOrder()) {
                // index number of vertex in topological order should start from 0, and increment by 1
                if (getRank(v) != r) {
                    return false;
                }
                r += 1;
            }
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = ranks.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

}
