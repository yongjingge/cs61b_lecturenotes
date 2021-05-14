package lecture36;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * This class provides static methods for sorting an array of w-character strings.
 * - Sort a String[] array of n extended ASCII strings (R = 256), each of length w.
 * @source https://algs4.cs.princeton.edu/51radix/LSD.java.html
 *
* */
public class RadixLSDStr {

    /* this class should not be instantiated */
    private RadixLSDStr() {}

    /* rearrange the array of w-character strings in ascending order */
    public static void sort(String[] a, int w) {

        if (a == null || a.length <= 1) {
            return;
        }

        int len = a.length;
        int radix = 256;
        String[] aux = new String[len];

        /* overall we need d round of counting sort,
        * sort by key-indexed counting on the d-th character */
        for (int d = w - 1; d >= 0; d -= 1) {
            // starting from the rightmost digit
            // we can initialize count array inside for-loop for each round of counting sort,
            // we can also initialize it outside the for-loop, and inside the loop after each round of sorting, we reset the count array so that it will be ready for the next round of counting sort!
            int[] count = new int[radix + 1];
            for (int i = 0; i < len; i += 1) {
                count[a[i].charAt(d) + 1] += 1;
            }

            // compute cumulates
            for (int r = 0; r < radix; r += 1) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < len; i += 1) {
                int countIndex = a[i].charAt(d);
                aux[count[countIndex]] = a[i];
                count[countIndex] += 1;
            }

            // copy back to the original array
            for (int i = 0; i < len; i += 1) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"bed", "bug", "dad", "yes", "zoo", "now", "for", "tip", "ilk", "sob",
                "nob", "sky", "hut", "egg", "wee", "dug", "was"};

        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (int i = 0; i < n; i++)
            assert a[i].length() == w : "Strings must have fixed length";

        // sort the strings
        sort(a, w);

        // print results
        for (int i = 0; i < n; i++)
            System.out.println(a[i]);
    }

}
