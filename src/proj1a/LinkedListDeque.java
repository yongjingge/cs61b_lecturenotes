package proj1a;

/* Build implementations of a double ended queue using LinkedList
* referencing lecture5 - DLList */
public class LinkedListDeque<T> implements Deque<T> {

    private ListNode sentinel;
    private int size;

    /* nested class: ListNode */
    private class ListNode{

        private T item;
        private ListNode prev;
        private ListNode next;
        public ListNode(T item, ListNode prev, ListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /* empty LLD */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* addFirst */
    @Override
    public void addFirst(T item) {
        sentinel.next = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* addLast */
    @Override
    public void addLast(T item) {
        sentinel.prev = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* removeFirst */
    @Override
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

    /* removeLast */
    @Override
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

    /* get Iteration */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        ListNode p = sentinel;
        for (int i = 0; i < size; i += 1) {
            p = p.next;
        }
        return p.next.item;
    }

    public T getRe(int index) {
        return helper(sentinel.next, index);
    }

    private T helper(ListNode x, int index) {
        if (index == 0) {
            return x.item;
        } else {
            return helper(x.next, index - 1);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ListNode p = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
}
