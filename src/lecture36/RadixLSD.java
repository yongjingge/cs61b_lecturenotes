package lecture36;

import java.util.Arrays;

/* This class provides static methods for sorting an array of Integer
* using Radix Sort (LSD).
* @source https://github.com/JourWon/sort-algorithm/blob/master/src/main/java/com/jourwon/sort/RadixSort.java
*  */
public class RadixLSD {

    /* this class should not be instantiated */
    private RadixLSD() {}

    /* main sort */
    public static void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        int len = arr.length;
        int radix = 10; // for Integer
        int[] aux = new int[len];
        int[] count = new int[radix + 1];

        /* get the width of the largest item in the array */
        int x = Arrays.stream(arr).map(s -> String.valueOf(s).length()).max().getAsInt();

        /* overall we need d round of counting sort */
        for (int d = 0; d < x; d += 1) {
            /* count frequencies of digit, counted digit's index starts from (digit + 1) */
            for (int i = 0; i < len; i += 1) {
                count[digitAt(arr[i], d) + 1] += 1;
            }

            /* compute cumulates: get the starting index of specific digit number */
            for (int i = 0; i < radix; i += 1) {
                count[i + 1] += count[i];
            }

            for (int i = 0; i < len; i += 1) {
                int countIndex = digitAt(arr[i], d);
                aux[count[countIndex]] = arr[i];
                count[countIndex] += 1;

                // aux[count[digitAt(arr[i], d)] ++] = arr[i];
            }

            for (int i = 0; i < len; i += 1) {
                arr[i] = aux[i];
            }

            // clear count for the next round of counting sort
            for (int i = 0; i < count.length; i += 1) {
                count[i] = 0;
            }
        }

    }

    /* get the specific digit of a given integer */
    private static int digitAt(int value, int d) {
        return (value / (int)Math.pow(10, d)) % 10;
    }

    /* test using main method */
    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
