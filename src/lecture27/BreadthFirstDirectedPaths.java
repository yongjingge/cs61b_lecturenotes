package lecture27;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/* This BFS class represents a data type for finding
* shortest paths (number of edges in the shortest path) from a source vertex or
* a set of source vertices to every other vertex in the graph.
*
* Given a graph G and source vertex s,
* is there a directed path from s to v?
* If so, find a shortest such path.
* @source https://algs4.cs.princeton.edu/42digraph/BreadthFirstDirectedPaths.java.html
*  */
public class BreadthFirstDirectedPaths {

    private static final int INIFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there a directed path from s to v?
    private int[] edgeTo; // record t he last edge on shortest s->v path in edgeTo[v]
    private int[] distTo; // record the length of shortest s->v path in distTo[v]

    /* Computes the shortest path from a single source vertex 's' and every other vertex in graph
    * @param G given graph
    * @param s the single source vertex 's' */
    public BreadthFirstDirectedPaths(Digraph G, int s) {
        int V = G.V(); // get number of vertices in the given graph
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        // initially set distTo target vertex to INIFINITY and change later
        for (int i = 0; i < V; i += 1) {
            distTo[i] = INIFINITY;
        }
        // check if source vertex is valid
        validateVertex(s);
        bfs(G, s);
    }

    /* Computes the shortest from any one of the source vertices to every other vertex
    * @param G given graph
    * @param sources a set of source vertices  */
    public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
        int V = G.V();
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int i = 0; i < V; i += 1) {
            distTo[i] = INIFINITY;
        }
        validateVertices(sources);
        bfs(G, sources);
    }

    /* BFS from single source
    * @param G given graph 'G'
    * @param s starting vertex 's' */
    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new LinkedList<>();
        marked[s] = true;
        distTo[s] = 0; // distance to 's'
        q.add(s);
        while (! q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (! marked[w]) {
                    marked[w] = true;
                    q.add(w);
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    /* BFS from a set of source vertices */
    private void bfs(Digraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new LinkedList<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.add(s);
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (! marked[w]) {
                    marked[w] = true;
                    q.add(w);
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    /* Is there a directed path from 's' to 'v'? */
    public boolean hasPathTo(int v) {
        // vertex 's' has already initialized in the constructor
        validateVertex(v);
        return marked[v];
    }

    /* Returns the number of edges in a shortest path from 's' to 'v' */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /* Checks if the vertex index is valid */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    /* Checks if vertices are all valid */
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        int countOfVertices = 0;
        for (Integer v : vertices) {
            countOfVertices += 1;
            if (v == null) {
                throw new IllegalArgumentException("vertex is null");
            }
            validateVertex(v);
        }
        if (countOfVertices == 0) {
            throw new IllegalArgumentException("zero vertices");
        }
    }

    /* Returns the shortest path from 's' to 'v' if exists */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (! hasPathTo(v)) {
            return null;
        }
        ArrayDeque<Integer> path = new ArrayDeque<>();
        int x;
        // this is similar to 'for (int x = v; x != s; x = edgeTo[x])
        // if distTo[x] != 0, it means that we haven't reached the source vertex
        // once vertex 'x' traverses backwards to meet source vertex 's', the distance from 'x' to 's' should be equal to 0!
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x); // in this case, source vertex has been put into the stack
        return path;
    }
}
