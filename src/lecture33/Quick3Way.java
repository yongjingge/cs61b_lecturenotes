package lecture33;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/* This class provides static methods for sorting an array of Comparable
 * using quicksort with 3-way partitioning.
 * @source https://algs4.cs.princeton.edu/23quicksort/Quick3way.java.html
 * */
public class Quick3Way {

    /* This class should not be instantiated */
    private Quick3Way() {}

    /* general sort */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int gt = hi;
        Comparable p = a[lo];
        int i = lo + 1; // i is used to traverse the whole array
        /* this is the 3-way partitioning algorithm */
        while (i <= gt) {
            int cmp = a[i].compareTo(p);
            if (cmp < 0) { // if a[i] is less than pivot
                swap(a, lt, i);
                i += 1;
                lt += 1;
            } else if (cmp > 0) { // if a[i] is greater than pivot
                swap(a, gt, i);
                gt -= 1;
            } else { // if a[i] is equal to pivot
                i += 1;
            }
        }

        sort(a, lo, lt - 1); // sort the part that all items are less than the pivot, starting from lo to lt-1
        sort(a, gt + 1, hi); // sort the part that all items are greater than the pivot, starting from gt+1 to hi
        // from lt to gt will be the items that are all equal to pivot
        assert isSorted(a, lo, hi);
    }

    /* helper method: swap */
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /* check if array is sorted */
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i += 1) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /* helper method: less */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /* test using main */
    public static void main(String[] args) {
        Integer[] a = { 9, -3, 5, 2, 6, 8, -6, 1, 3 };
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
