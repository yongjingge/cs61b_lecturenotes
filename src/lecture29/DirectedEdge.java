package lecture29;

/* Represent a weighted edge in an EdgeWeightedDigraph */
public class DirectedEdge {

    private final int v;
    private final int w;
    private final double weight;

    /* constructor: weight has to be non-negative */
    public DirectedEdge(int v, int w, double weight) {
        if (v < 0 || w < 0) {
            throw new IllegalArgumentException("Vertex names must be non-negative integers");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException(" Weight is not a number");
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /* return the tail vertex of the directed edge
    * edge v -> w, will return v */
    public int from() {
        return v;
    }

    /* return the head vertex of the directed edge
    * edge v-> w, will return w */
    public int to() {
        return w;
    }

    /* return the weight */
    public double weight() {
        return weight;
    }

}
