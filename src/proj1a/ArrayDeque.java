package proj1a;

/* Build implementations of a double ended queue using Array
 * referencing lecture7 - AD */
public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITCAP = 8;
    private static final double RATIO = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[INITCAP];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int minusOne(int i) {
        return (i + items.length - 1) % items.length;
    }

    private int plusOne(int i) {
        return (i + 1) % items.length;
    }

    private void resize(int newCap) {
        T[] newArr = (T[]) new Object[newCap]; // index from 0 through (newCap-1)
        int curFirst = plusOne(nextFirst);
        for (int i = 0; i < size; i += 1) {
            newArr[i] = items[curFirst];
            curFirst = plusOne(curFirst);
        }
        items = newArr;
        nextFirst = newCap - 1;
        nextLast = size;
    }

    private void upSize() {
        resize(items.length * 2);
    }

    private void downSize() {
        resize(items.length / 2);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            upSize();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            upSize();
        }
        items[nextLast] = item;
        nextLast =plusOne(nextLast);
        size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int curFirst = plusOne(nextFirst);
        T removed = items[curFirst];
        items[curFirst] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (items.length >= 16 && size < items.length * RATIO) {
            downSize();
        }
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int curLast = minusOne(nextLast);
        T removed = items[curLast];
        items[curLast] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length > 16 && size < items.length * RATIO) {
            downSize();
        }
        return removed;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        index = (plusOne(nextFirst) + index) % items.length;
        return items[index];
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
    }
}
