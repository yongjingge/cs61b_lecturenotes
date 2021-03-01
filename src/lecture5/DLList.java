package lecture5;

/* Doubly Linked List
* make your list circular, with a single sentinel in the middle */
public class DLList {

    private IntNode sentinel;
    private int size;

    public DLList() {
        sentinel = new IntNode(35, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public static void main(String[] args) {
        DLList L = new DLList();
        L.addLast(19);
        L.addFirst(10);
        L.addLast(20);
        L.addFirst(9);
        L.print();
        System.out.println("add 22 to the end of the list, after that we get: ");
        L.addLast(22);
        L.print();

        System.out.println("remove first item which is: " + L.removeFirst());
        L.print();

        System.out.println("remove last item which is: " + L.removeLast());
        L.print();
    }

    public void addFirst(int x) {
        size += 1;
        sentinel.next = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next; // first.next.prev = first
    }

    public void addLast(int x) {
        size += 1;
        sentinel.prev = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev; // last.prev.next = last;
    }

    public int removeFirst() {
        if (size == 0) {
            return 0;
        }
        size -= 1;
        int first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return first;
    }

    public int removeLast() {
        if (size == 0) {
            return 0;
        }
        size -= 1;
        int last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return last;
    }

    public int get(int x) {
        if (size == 0) {
            return 0;
        }
        IntNode p = sentinel;
        for (int i = 0; i < x; i += 1) {
            p = p.next;
        }
        return p.next.item;
    }

    public void print() {
        IntNode cur = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
