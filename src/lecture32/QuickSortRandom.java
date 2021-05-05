package lecture32;

/* This class provides static methods for sorting an array of Comparable
* using quick sort.
* It picks pivots randomly to ensure a N logN runtime.
*  */
public class QuickSortRandom {

    /* this class should not be instantiated. */
    private QuickSortRandom() {}

    /* general sort */
    public static void sort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void quickSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int pivotIndex = partition(a, lo, hi);
        quickSort(a, lo, pivotIndex - 1);
        quickSort(a, pivotIndex + 1, hi);
        assert isSorted(a, lo, hi);
    }

    /* partition: 1. partition on a random pivot 2. rearrange the array
     * this method will return the index of the pivot after rearrange is done.
     */
     private static int partition(Comparable[] a, int lo, int hi) {
         // first, randomly pick an index as the pivot, and record its value
         int pivotIndex = lo + (int)(Math.random() * (hi - lo + 1));
         Comparable pivotValue = a[pivotIndex];

         // second, swap the chosen pivot to the end of the array, then rearrange the array so that pivot is in its right place.
         swap(a, pivotIndex, hi);

         // rearrange the array so that all items to the left of pivot are smaller than it,
         // and all items to the right of pivot are greater than it.
         int i, j; // i is used to mark the left-hand side items' index, j is used to traverse the array
         for (i = lo, j = lo; j < hi; j += 1) {
             // no need to compare the pivot with itself so we set j < hi as the condition
             if (less(a[j], pivotValue)) {
                 // if a[j] is smaller than pivot
                 swap(a, j, i);
                 i += 1;
             }
         }
         swap(a, i, j); // at this time, j is at the index of pivot (hi), i is where the pivot should be at
         return i;
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
