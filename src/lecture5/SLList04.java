package lecture5;

/* SLList04 demonstrating the incorporation of Generic types */
public class SLList04<Stuff> {

    /* Nested Class StuffNode */
    protected class StuffNode {
        Stuff item;
        StuffNode next;

        public StuffNode(Stuff item, StuffNode next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return item + " " + next;
        }
    }

    private StuffNode first;
    private int size;

    public SLList04(Stuff x) {
        first = new StuffNode(x, null);
        size = 1;
    }

    public SLList04() {
        first = null;
        size = 0;
    }

    public void addFirst(Stuff x) {
        size += 1;
        first = new StuffNode(x, first);
    }

    public Stuff getFirst() {
        return first.item;
    }

    public void addLast(Stuff x) {
        size += 1;
        if (first == null) {
            first = new StuffNode(x, null);
            return;
        }
        StuffNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new StuffNode(x, null);
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return first + " ";
    }

    public static void main(String[] args) {
        SLList04<String> L = new SLList04<>();
        L.addLast("thugs");
        L.addFirst("head");
        System.out.println(L.toString());
        System.out.println(L.size());

        SLList04<Integer> Q = new SLList04<>();
        Q.addLast(11);
        Q.addFirst(15);
        System.out.println(Q.toString());
        System.out.println(Q.size());
    }
}
