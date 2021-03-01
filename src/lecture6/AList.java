package lecture6;

import java.util.Arrays;

/* Array-based list
* INVARIANTS of AList:
* addLast: the next item we want to add, will go to position 'size';
* size: the number of items in the list should be 'size';
* getLast: the item we want to return, is in position 'size-1'.
*
* e.g.
* numbers: 1 2 3 4 5
* index:   0 1 2 3 4
* items:  [0 0 0 0 0]
* size: 5
*  */
public class AList {

    private int[] items;
    private int size;

    public AList() {
        items = new int[100]; // an array of fixed size of 100
        size = 0;
    }

    /* resize the array */
    public void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /* insert x into the back of the list */
    public void addLast(int x) {
        /* we can do resize either via a separate helper method 'resize',
         * or we can do it inside this addLast method */
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    /* return the item from the back of the list */
    public int getLast() {
        return items[size - 1];
    }

    /* return the i-th item from the list */
    public int get(int x) {
        return items[x];
    }

    /* return the number of items in the list */
    public int getSize() {
        return size;
    }

    /* delete and return the item from the back of the list */
    public int removeLast() {
        int res = getLast();
        size -= 1;
        return res;
    }

    public void efficientResize() {
        double R = size / items.length;
        if (R < 0.25) {
            resize(items.length / 2);
        }
    }

    public void print() {
        for (int i = 0; i < getSize(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AList arr = new AList();
        arr.addLast(11);
        arr.addLast(22);
        arr.addLast(33);
        System.out.print("The arr is: ");
        arr.print();

        System.out.println("Its last item is: " + arr.getLast());
        System.out.println("Its size is: " + arr.getSize());
        System.out.println("Index 1 of arr is: " + arr.get(1));
        System.out.println("Remove its last item and return it: " + arr.removeLast());
        System.out.println("After removing the last item, its size is: " + arr.getSize());
        System.out.print("After removing the last item, the arr is: ");
        arr.print();

        arr.efficientResize();
        System.out.print("After efficient resizing, the arr is: ");
        arr.print();
    }
}
