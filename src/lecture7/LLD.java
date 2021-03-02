package lecture7;

/* LinkedListDeque
* - constructed by ListNode
* - implemented in a circular way
* Double-ended queues are sequence of containers with dynamic size
* 双向链表 理解：双向链表的插入/删除需要修改两次指针prev/next
* 通过双向链表实现双向队列结构
*  */
public class LLD<T> {

    private ListNode sentinel;
    private int size;

    /* nested class ListNode */
    private class ListNode {
        private T item;
        private ListNode prev;
        private ListNode next;

        public ListNode(T item, ListNode prev, ListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LLD() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        sentinel.next = new ListNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T x) {
        sentinel.prev = new ListNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }

    public T iterativeGet(int x) {
        if (isEmpty()) {
            return null;
        }
        ListNode p = sentinel.next;
        for (int i = 0; i < x; i += 1) {
            p = p.next;
        }
        return p.item;
//        ListNode p = sentinel;
//        for (int i = 0; i < x; i += 1) {
//            p = p.next;
//        }
//        return p.next.item;
    }

    public T recursiveGet(int x) {
        if (isEmpty()) {
            return null;
        }
        return recursiveGetHelper(sentinel.next, x);
    }

    /* helper method */
    private T recursiveGetHelper(ListNode p, int x) {
        if (x == 0) {
            return p.item;
        }
        return recursiveGetHelper(p.next, x - 1);
    }

    public void print() {
        ListNode p = sentinel.next;
        for (int i = 0; i < getSize(); i += 1) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LLD<Integer> d1 = new LLD<>();
        d1.addFirst(3);
        d1.addFirst(2);
        d1.addFirst(1);
        d1.addLast(4);
        System.out.println(d1.getSize());
        d1.print();

        System.out.println(d1.iterativeGet(1));
        System.out.println(d1.iterativeGet(3));
        System.out.println(d1.recursiveGet(1));
        System.out.println(d1.recursiveGet(3));

        System.out.println("Removing the first item: " + d1.removeFirst());
        d1.print();
        System.out.println("Removing the last item: " + d1.removeLast());
        d1.print();
        System.out.println(d1.getSize());
        System.out.println(d1.iterativeGet(0));
    }
}
