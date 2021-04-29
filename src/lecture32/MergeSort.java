package lecture32;

/* This class provides static methods for sorting an array of Comparable
* using a top-down recursive version of merge sort.
* It is a stable sorting algorithm.
* @source https://algs4.cs.princeton.edu/22mergesort/Merge.java.html
*   */
public class MergeSort {

    /* MergeSort.sort(provide an unsorted array) method
    * rearrange the array in ascending order, using the natural order. */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        assert isSorted(a);
    }

    /* mergesort a[lo...hi] using auxiliary array aux[lo...hi] */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi); // merge two sorted sub-arrays
    }

    /* stably merge a[lo...mid] with a[mid+1...hi] using aux[] */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo...mid] and a[mid+1...hi] are already sorted
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // copy to aux, eventually we need to merge from aux to a
        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
        }

        // merge back to a, mark starting indices of two sub-arrays
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                // a[lo...mid] is all merged, we just need to add a[mid+1...hi](which is already sorted) into the result array
                a[k] = aux[j];
                j += 1;
            } else if (j > hi) {
                // a[mid+1...hi] is all merged, we just need to add a[lo...mid](which is already sorted) into the result array
                a[k] = aux[i];
                i += 1;
            } else if (less(aux[j], aux[i])) {
                // when item in a[mid+1...hi] is less than corresponding item in a[lo...mid], we add aux[j] into the result
                a[k] = aux[j];
                j += 1;
            } else {
                // when item in a[mid+1...hi] is greater than corresponding item in a[lo...mid], we add aux[i] into the result
                a[k] = aux[i];
                i += 1;
            }
        }
        assert isSorted(a, lo, hi);
    }

    /* helper method: less */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /* helper method: check if array is sorted */
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i += 1) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

}
