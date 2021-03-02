package lecture7;

/* a test-driven development example of SelectionSort */
public class Sort {

    /* sort strings destructively */
    public static void sort(String[] s) {
        /* find the smallest item */
        /* move it to the front */
        /* selection sort the rest using recursion */
        if (s == null || s.length <= 1) {
            return;
        }
        sort(s, 0);
    }

    public static int findSmallest(String[] s, int start) {
        int smallestIndex = start;
        for (int i = start; i < s.length; i += 1) {
            int cmp = s[i].compareTo(s[smallestIndex]);
            if (cmp < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static void swap(String[] s, int i, int j) {
        String tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    /* a helper method to conduct recursion */
    public static void sort(String[] s, int start) {
        if (start == s.length) {
            return;
        }
        int smallestIndex = findSmallest(s, start);
        swap(s, start, smallestIndex);
        sort(s, start + 1);
    }
}
