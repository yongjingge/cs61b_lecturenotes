package lecture28;

import lecture27.Digraph;

import java.util.ArrayDeque;

/* Determines whether a diagraph has a directed cycle.
* @source https://algs4.cs.princeton.edu/42digraph/DirectedCycle.java.html
* this implementation uses depth-first search */
public class DirectedCycle {

    private boolean[] marked; // marked[v] = has vertex v been marked?
    private int[] edgeTo; // edgeTo[v] = previous one vertex on path to reach vertex v
    private boolean[] OnStack; // OnStack[v] = is vertex v on the stack below?
    private ArrayDeque<Integer> cycle; // directed cycle represented by STACK if has one

    /* determines whether the graph has a directed cycle,
    * if so, find such a cycle. */
    public DirectedCycle(Digraph G) {
        int V = G.V();
        marked = new boolean[V];
        edgeTo = new int[V];
        OnStack = new boolean[V];
        for (int i = 0; i < V; i += 1) {
            if (! marked[i] && cycle == null) {
                dfs(G, i);
            }
        }
    }

    /* run DFS and find a directed cycle if has one */
    private void dfs(Digraph G, int i) {
        OnStack[i] = true;
        marked[i] = true;
        // traverse vertex i's adjacent vertices
        for (int w : G.adj(i)) {
            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            } else if (! marked[w]) { // directed edge is i->w
                edgeTo[w] = i;
                dfs(G, w);
            } else if (OnStack[w]) { // meaning vertex w has been traversed twice --> a cycle formed
                cycle = new ArrayDeque<>();
                for (int x = i; x != w; x = edgeTo[x]) {
                    // backwards traverse the graph from vertex i to one previous to vertex w
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(i);
                assert check();
            }
        }
        OnStack[i] = false;
    }

    /* does the graph have a directed cycle? */
    public boolean hasCycle() {
        return cycle != null;
    }

    /* returns a directed cycle if has one */
    public Iterable<Integer> getCycle() {
        return cycle;
    }

    /* certify that graph has a directed cycle */
    private boolean check() {
        if (hasCycle()) {
            // verify the index
            int first = -1;
            int last = -1;
            for (int v : getCycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            // if first == last after all vertices in getCycle() being traversed,
            // it means that there is a directed cycle.
            if (first != last) {
                return false;
            }
        }
        return true;
    }
}
