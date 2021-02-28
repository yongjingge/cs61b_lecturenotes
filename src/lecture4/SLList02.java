package lecture4;

/* SLList02 showing the use of a 'size' attribute to record the size of the list */
public class SLList02 {

    private IntNode first;
    private int size;

    /* constructor */
    public SLList02(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /* constructor of an empty list */
    public SLList02() {
        first = null;
        size = 0;
    }

    public int getFirst() {
        return first.item;
    }

    public void addFirst(int x) {
        size += 1;
        first = new IntNode(x, first);
    }

    public void addLast(int x) {
        size += 1;
        if (first == null) {
            first = new IntNode(x, null);
            return;
        }
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public int size() {
        return size;
    }

    public String toString() {
        return first + " ";
    }

    public static void main(String[] args) {
        SLList02 p = new SLList02(20);
        p.addLast(30);
        p.addLast(40);
        p.addFirst(10);
        System.out.println(p.size());
        System.out.println(p.getFirst());
        System.out.println(p.toString());

        SLList02 l = new SLList02();
        System.out.println(l.toString());
        System.out.println(l.size());
        l.addLast(10);
        System.out.println(l.toString());
        System.out.println(l.size());
    }

}
