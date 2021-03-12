package lecture20;

/* Approach Four:
* modify the 'find' method so that
* it records the 'boss' information. */
public class WeightedQuickUnionWithPathCompression implements DisjointSets {
    private int[] parents;
    private int[] size;

    /* constructor: a DisjointSets with N items */
    public WeightedQuickUnionWithPathCompression(int N) {
        parents = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i += 1) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    /* records and returns the 'boss' node */
    private int find(int p) {
        /* 'boss' node: will return itself directly */
        if (p == parents[p]) {
            return p;
        }
        /* if not equal, will call recursion until we got the 'boss' node. */
        parents[p] = find(parents[p]);
        return parents[p];
    }

    @Override
    public void connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] < size[rootQ]) {
            parents[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
