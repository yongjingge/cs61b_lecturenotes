package lecture33;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/* This class provides static methods for sorting an array of Comparable
 * using quicksort with Tony Hoare's partitioning.
 * */
public class QuickSortHoare2 {

    /* this class should not be instantiated */
    private QuickSortHoare2() {}

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
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
        assert isSorted(a, lo, hi);
    }

    /* partition using Tony Hoare's scheme */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable pivot = a[lo];
        while (true) {
            while(less(a[++ i], pivot)) {
                if (i == hi) {
                    break;
                }
            }
            while(less(pivot, a[-- j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j); // when two pointers both stopped
        }
        // while i >= j, the loop break
        swap(a, lo, j);
        return j;
    }

    /* helper method: less */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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

    /* test using main */
    public static void main(String[] args) {
        Integer[] a = { 9, -3, 5, 2, 6, 8, -6, 1, 3 };
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
