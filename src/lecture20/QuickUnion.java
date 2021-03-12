package lecture20;

/* Approach Two:
* Representing everything as 'connected components'.
* Combining two subsets into union requires
* changing only one value, this is achieved by
* assigning each node a parent! */
public class QuickUnion implements DisjointSets {
    private int[] parents;

    /* constructor: a DisjointSets with N items */
    public QuickUnion(int N) {
        parents = new int[N];
        for (int i = 0; i < N; i += 1) {
            parents[i] = i;
        }
    }

    /* returns the parent node (root parent) of node p,
    * if parents[p] == p, p is the root node */
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
        parents[proot] = qroot; // change parent node of proot to qroot
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

}
