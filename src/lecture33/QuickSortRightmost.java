package lecture32;

/* This class provides static methods for sorting an array of Comparable
 * using quick sort.
 * It picks the rightmost item as pivot.
 *  */
public class QuickSortRightmost {

    /* this class should not be instantiated. */
    private QuickSortRightmost() {}

    /* general sort */
    public static void sort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void quickSort(Comparable[] a, int lo, int hi) {
        // base case
        if (hi <= lo) {
            return;
        }
        Comparable pivot = a[hi]; // choose the rightmost item as pivot
        int index = lo; // record the left-hand side items' index
        for (int i = lo; i < hi; i += 1) {
            if (less(a[i], pivot)) {
                swap(a, i, index);
                index += 1;
            }
        }
        swap(a, index, hi); // can also be swap(a, index, i) if we initialize variable i outside the for loop
        // now index is the index of pivot after rearrangement is done
        quickSort(a, lo, index - 1);
        quickSort(a, index + 1, hi);
    }

    /* helper method: swap */
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /* helper method: less */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
