package lecture20;

/* Approach Three:
* modify QuickUnion to avoid tall trees.
* Weight is based on the height of each subtree. */
public class WeightedQuickUnionByHeight implements DisjointSets {
    private int[] parents;
    private int[] heights;

    /* constructor */
    public WeightedQuickUnionByHeight(int N) {
        parents = new int[N];
        heights = new int[N];
        for (int i = 0; i < N; i += 1) {
            parents[i] = i;
            heights[i] = 0;
        }
    }

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
        /* make shorter root point to taller one */
        if (heights[proot] < heights[qroot]) {
            /* notice that adding shorter tree to taller one
            * WON'T CHANGE THE taller one's HEIGHT! */
            parents[proot] = qroot;
        } else if (heights[proot] > heights[qroot]) {
            parents[qroot] = proot;
        } else {
            /* if both trees are of the same height,
            * add one to another, the root one need to increment its height by 1. */
            parents[qroot] = proot;
            heights[proot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
