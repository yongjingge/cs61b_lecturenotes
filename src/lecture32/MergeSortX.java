package lecture32;

/* This class provides static methods for sorting an array of Comparable
* using an optimized version of mergesort.
* In worst case the runtime is N logN.
* It is a stable sorting algorithm.
* It is optimized to switch to 'Insertion Sort' for small subarrays (cutoff: 7).
* @source https://algs4.cs.princeton.edu/22mergesort/MergeX.java.html
*  */
public class MergeSortX {

    private static final int CUTOFF = 7; // cutoff to insertion sort
    /* This class should not be instantiated. */
    private MergeSortX() {}

    /* Stably merge two sorted subarrays
    * same as the general mergesort algorithm,
    * note that we have cloned original array into aux in the sort method,
    * and the cloned array aux serves as the source array. */
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        // precondition: src[lo...mid] and src[mid+1...hi] are sorted subarrays
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid + 1, hi);
        //mark the starting index of both subarrays
        int i = lo;
        int j = mid + 1;
        // src is array aux, dst is array a
        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                // src[lo...mid] is all merged
                dst[k] = src[j++];
            } else if (j > hi) {
                // src[mid+1...hi] is all merged
                dst[k] = src[i++];
            } else if (less(src[j], src[i])) {
                // when src[j] is less than src[i], src[j] is merged first into the dst array,
                // and index j should be incremented by 1.
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
        // postcondition: dst[lo...hi] is a sorted array!
        assert isSorted(dst, lo, hi);
    }

    /* Rearrange the array in ascending order, using the natural order. */
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
        assert isSorted(a);
    }
    /* actual sort */
    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // src is array aux (cloned), dst is array a (original)
        // improvement one: use insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi); // dst is array a, the original array
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        // improvement two: test whether array is already in order.
        // can reduce runtime to be linear by adding a test to skip call to merge()
        // if a[mid] is less than or equal to a[mid+1].
        if (! less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return; // in this case, no need to call the merge() method
        }

        merge(src, dst, lo, mid, hi);
    }

    /* Insertion sort */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i += 1) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j -= 1) {
                swap(a, j, j - 1);
            }
        }
    }

    /* helper method: swap */
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /* helper method: less */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0; // return true if a is less than be
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
