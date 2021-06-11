package lecture28;

import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/* Finding shortest paths from a source vertex to every other vertex in the graph */
public class BreadthFirstDirectedPaths {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] shows whether there is a directed path from source to vertex v
    private int[] edgeTo; // record the last edge on the shortest s-to-v path in edgeTo[v]
    private int[] distTo; // record the length of the shortest s-to-v path in distTo[v]

    /* constructor */
    public BreadthFirstDirectedPaths(Digraph G, int s) {
        int V = G.V();
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int i = 0; i < V; i += 1) {
            distTo[i] = INFINITY; // initially distTo vertex i is set to be infinite.
        }
        validate(s); // validate the starting vertex
        bfs(G, s); // call bfs method with the starting vertex
    }

    // bfs main method
    private void bfs(Digraph G, int v) {
        Queue<Integer> fringe = new LinkedList<>();
        marked[v] = true;
        distTo[v] = 0; // the starting vertex
        fringe.add(v); // initialize the fringe with a starting vertex

        while(! fringe.isEmpty()) {
            int i = fringe.poll();
            for (int w : G.adj(i)) {
                if (! marked[w]) {
                    marked[w] = true;
                    fringe.add(w);
                    edgeTo[w] = i;
                    distTo[w] = distTo[i] + 1;
                }
            }
        }
    }

    private void validate(int s) {
        int V = marked.length;
        if (s >= V || s < 0) {
            throw new IllegalArgumentException("given vertex is invalid");
        }
    }

    public boolean hasPathTo(int v) {
        validate(v);
        return marked[v]; // if !marked[v], vertex v is not reachable from the source vertex
    }

    public int distTo(int v) {
        validate(v);
        return distTo[v];
    }

    /* return the shortest path from 's' to 'v' if exists */
    public Iterable<Integer> pathTo(int v) {
        validate(v);
        if (! hasPathTo(v)) {
            return null;
        }

        ArrayDeque<Integer> paths = new ArrayDeque<>();
        int x;
        // traverse backwards from the target vertex [v]
        for (x = v; distTo[x] != 0; x = edgeTo[x]) { // if we have a variable initially record the starting vertex, we can use x != s as one of the conditions
            paths.push(x);
        }
        paths.push(x); // when distTo[x] == 0, we have reached the source vertex, and add it to the paths stack!
        return paths;
    }
}
