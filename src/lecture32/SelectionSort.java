package lecture32;

import java.util.Arrays;

/* This class provides static methods for sorting an array of Comparable
* using Selection Sort.
* This implementation makes compares to sort any array of length N, and
* it performs exactly N swaps.
* It is not a stable sorting algorithm.
* @source https://algs4.cs.princeton.edu/21elementary/Selection.java.html
*  */
public class SelectionSort {

    /* This class should not be instantiated. */
    private SelectionSort() {}

    public static void sort(Comparable[] a) {
        int len = a.length;
        // using nested for-loops
        for (int i = 0; i < len; i += 1) {
            int min = i; // record minimum item's index
            for (int j = i + 1; j < len; j += 1) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    /* helper method: less */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /* helper method: swap */
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /* check if array is fully sorted */
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    /* check if the array is sorted from array[lo] to array[hi] */
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i += 1) {
            if (less(a[i], a[i-1])) {
                // if a[lo+1] is less than a[lo], inversion exists
                return false;
            }
        }
        return true;
    }

    /* print out the sorted result */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i += 1) {
            System.out.println(a[i]);
        }
    }

    /* test using main */
    public static void main(String[] args) {
        Integer[] a = { 9, -3, 5, 2, 6, 8, -6, 1, 3 };
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
