package lecture4;

/* SLList03 demonstrating the use of Sentinel Node to avoid NullPointer */
public class SLList03 {

    /* The first item would be at sentinel.next if exists */
    private IntNode sentinel;
    private int size;

    public SLList03(int x) {
        sentinel = new IntNode(35, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public SLList03() {
        sentinel = new IntNode(35, null);
        size = 0;
    }

    public void addFirst(int x) {
        size += 1;
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public int size() {
        return size;
    }

    public String toString() {
        return sentinel.next.toString();
    }

    public static void main(String[] args) {
        SLList03 p = new SLList03();
        System.out.println(p.size());
        p.addFirst(1);
        p.addLast(2);
        System.out.println(p.toString());
        System.out.println(p.size());
        System.out.println(p.getFirst());
    }
}
