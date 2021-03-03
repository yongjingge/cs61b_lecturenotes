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

    protected Stuff removeLast() {
        size -= 1;
        if (first == null) {
            return null;
        }
        StuffNode p = first;
        /* walk through to the previous one of the last item
        * this is important, because if we are nulling out the deleted item, we need to access the previous item,
        * and remove its next pointer by making it point to null */
        while (p.next.next != null) {
            p = p.next;
        }
        /* now p is at the last.prev position */
        Stuff removed = p.next.item;
        p.next = null;
        return removed;
    }

    public void printSLList() {
        if (first == null) {
            return;
        }
        StuffNode p = first;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SLList04<String> L = new SLList04<>();
        L.addLast("thugs");
        L.addFirst("head");
        L.printSLList();
        System.out.println(L.size());

        SLList04<Integer> Q = new SLList04<>();
        Q.printSLList();
        Q.addLast(11);
        Q.addFirst(15);
        Q.addLast(13);
        System.out.print("Before removing the last item, the SLList is ");
        Q.printSLList();
        System.out.println("Before removing the last item, the size is " + Q.size());
        Q.removeLast();
        System.out.print("After removing the last item, the SLList is ");
        Q.printSLList();
        System.out.println("After removing the last item, the size is " + Q.size());
    }
}
