package lecture7;

import java.util.Arrays;

/* ArrayDeque
* implemented in a circular way
*
* 底层通过数组实现，并且是循环数组，即数组中任意index可以视为起点/终点
* nextFirst指向下一个可以插入元素的起点位置，即addFirst的时候，从nextFirst位置插入，并且前移nextFirst
* nextLast指向下一个可以插入元素的终点位置，即addLast的时候，从nextLast位置插入，并且后移nextLast
* */
public class AD<T> {
    private T[] items;
    private int size;
    private int nextFirst; // index of next first
    private int nextLast; // index of next last

    private static final int INIT_CAP = 8;
    private static final double MIN_RATIO = 0.25;

    public AD() {
        items = (T[]) new Object[INIT_CAP];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /* move backwards index by 1 */
    private int minusOne(int index) {
        // index + items.length to prevent a negative number after - 1
        return (index + items.length - 1) % items.length;
    }

    /* move forwards index by 1 */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /* resize */
    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        /* need to find current first index through the 'nextFirst' attribute */
        int curFirst = (plusOne(nextFirst));
        for (int i = 0; i < size; i += 1) {
            newArr[i] = items[curFirst];
            curFirst = plusOne(curFirst);
        }
        items = newArr;
        /* the new nextFirst will be at the end of the array's boxes */
        nextFirst = capacity - 1;
        /* the new nextLast will be one bit after current last item which is at [size-1] */
        nextLast = size;
    }

    private void upSize() {
        /* upsize happens when size == items.length, so here we can use size */
        resize(size * 2);
    }

    private void downSize() {
        resize(items.length / 2);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * understanding of minusOne and plusOne
     * length: 5
     * index: 0 1 2 3 4
     * index:     *
     * 2+5-1=6 6%5=1 minusOne
     * 2+1=3 3%5=3 plusOne
     * if index = 4
     * 4+1=5 5%5=0 plusOne
     * 4+5-1=8 8%5=3 minusOne
     */

    /* add to the front of the deque */
    public void addFirst(T x) {
        if (size == items.length) {
            upSize();
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /* add to the back of the deque */
    public void addLast(T x) {
        if (size == items.length) {
            upSize();
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /* remove and return the first item of the deque */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        /* need to find current first index through the 'nextFirst' attribute */
        int curFirst = plusOne(nextFirst);
        T removed = items[curFirst];
        /* nulling out the deleted item */
        items[curFirst] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (items.length >= 16 && size < items.length * MIN_RATIO) {
            downSize();
        }
        return removed;
    }

    /* remove and return the last item of the deque */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        /* need to find current last index through the 'nextLast' attribute */
        int curLast = minusOne(nextLast);
        T removed = items[curLast];
        items[curLast] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length > 16 && size < items.length * MIN_RATIO) {
            downSize();
        }
        return removed;
    }

    /* get the i-th item of the deque */
    public T get(int i) {
        if (i > size) {
            return null;
        }
        i = (plusOne(nextFirst) + i) % items.length;
        return items[i];
    }

    public int getSize() {
        return size;
    }

    public void printDeque() {
        /* may not print from the first item, and may include nulls */
        System.out.println(Arrays.toString(items));
    }

    public void printFromFirst() {
        if (isEmpty()) {
            return;
        }
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AD<Integer> a1 = new AD<>();
        a1.addFirst(0);
        // a1.removeFirst();
        a1.addFirst(2);
        // a1.removeFirst();
        a1.addLast(4);
        System.out.println(a1.removeFirst());
        a1.addFirst(6);
        a1.addLast(7);
        a1.addLast(8);
        System.out.println(a1.removeFirst());
        a1.addFirst(10);
        a1.addFirst(11);
        System.out.println(a1.get(2));
        a1.get(1);
        a1.addFirst(14);
        a1.addFirst(15);
        a1.addLast(16);
        a1.printDeque();
        a1.get(2);
        a1.addFirst(18);
        a1.addFirst(19);
        System.out.println(a1.removeLast());
        a1.printDeque();
        System.out.println("Print from the first, the queue is ");
        a1.printFromFirst();
        System.out.println("The item in index0 is " + a1.get(0));
        System.out.println("The item in index2 is " + a1.get(2));
        System.out.println("The first item is at index " + a1.plusOne(a1.nextFirst));
        System.out.println("The last item is at index " + a1.minusOne(a1.nextLast));
    }
}
