package lecture20;

/* Using an Array to track sets */
public class QuickFindDS implements DisjointSets {
    private int[] track;

    /* constructor: a DisjointSets with N items */
    public QuickFindDS(int N) {
        track = new int[N];
        for (int i = 0; i < N; i += 1) {
            track[i] = i;
        }
    }

    /* index is the item itself, its value is its root's value.
    * when we connect two subsets, we change entries that equal track[p] to track[q]. */
    @Override
    public void connect(int p, int q) {
        int proot = track[p];
        int qroot = track[q];
        for (int i = 0; i < track.length; i += 1) {
            if (track[i] == proot) {
                track[i] = qroot;
            }
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return track[p] == track[q];
    }
}
