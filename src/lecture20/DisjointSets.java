package lecture20;

/* ADT for Disjoint Sets
* will provide four different implementations */
public interface DisjointSets {
    /* connects two items P and Q */
    void connect(int p, int q);
    /* checks to see if two items are connected */
    boolean isConnected(int p, int q);
}
