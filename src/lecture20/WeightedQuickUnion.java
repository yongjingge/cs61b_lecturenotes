package lecture20;

/* Approach Three:
* modify QuickUnion to avoid tall trees by
* tracking tree size using an array,
* and always link root of smaller tree to larger tree.
* Weight is based on the size of each subset. */
public class WeightedQuickUnion implements DisjointSets {
    private int[] parents;
    /* track number of elements in subtree rooted at i. */
    private int[] size;

    /* constructor: a DisjointSets with N items */
    public WeightedQuickUnion(int N) {
        parents = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i += 1) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    /* returns the parent node (root parent) of node p */
    private int find(int p) {
        while (p != parents[p]) {
            p = parents[p];
        }
        return p;
    }

    @Override
    public void connect(int p, int q) {
        int proot = find(p);
        int qroot = find(q);
        if (proot == qroot) {
            return;
        }
        /* make the smaller tree's root point to the larger one's */
        if (size[proot] < size[qroot]) {
            parents[proot] = qroot;
            size[qroot] += size[proot];
        } else {
            parents[qroot] = proot;
            size[proot] += size[qroot];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
