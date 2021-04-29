package lecture32;

/* This class provides static methods for sorting an array of Comparable
* using insertion sort.
* It is a stable sorting algorithm.
* @source https://algs4.cs.princeton.edu/21elementary/Insertion.java.html
*  */
public class InsertionSort {

    /* this class should not be instantiated. */
    private InsertionSort() {}

    /* general sort */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i += 1) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j-= 1) {
                swap(a, j, j - 1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a, lo, hi);
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
}
