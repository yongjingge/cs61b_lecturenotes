package lecture5;

import java.util.Arrays;

/* Array Basics, 1D Array, 2D Array */
public class Array {

    public static void main(String[] args) {
        /* 1D Arrays */
        int[] x = new int[]{1, 2, 3, 4, 5};
        int[] y = x; // reference types, any changes in y will happen in x as well
        System.out.println("The 'y = x' operation makes y pointing at the same Array address as x does.");
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));
        int[] z = new int[4];
        z[2] = x[2]; // primitive types, changes in z[2] will not influence x[2]
        z[2] = - z[2];
        System.out.println("x[2] is " + x[2]);
        System.out.println("z[2] is " + z[2]);

        x = new int[4];
        y = new int[3];
        System.out.println("Both x and y are pointing at new Array addresses, the original array {1,2,3,4,5} has lost its references.");
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));

        /* System.arraycopy */
        x = new int[]{11, 31, 22, 5, 7};
        System.out.println(Arrays.toString(x));
        int[] b = {14, 25, 3};
        System.arraycopy(b, 0, x, 2, 3);
        System.out.println(Arrays.toString(x));

        /* 2D Arrays */
        int[][] arr = new int[4][];
        System.out.println(Arrays.deepToString(arr));
        arr[0] = new int[]{1};
        arr[1] = new int[]{1, 2};
        arr[2] = new int[]{1, 2, 3};
        arr[3] = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.deepToString(arr));
        int[] rowTwo = arr[1];
        rowTwo[1] = -1;
        System.out.println(Arrays.deepToString(arr));

        int[][] matrix = new int[4][4];
        System.out.println(Arrays.deepToString(matrix));
        matrix = new int[][]{{1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}};
        System.out.println(Arrays.deepToString(matrix));

        int[][] xx = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] zz = new int[3][];
        zz[0] = xx[0]; // Array of Array addresses, it is reference type compared with 1D Array, which will merely change the primitive type.
        zz[0][0] = - zz[0][0]; // will also change the value of xx[0][0] to a negative number

        int[][]ww = new int[3][3];
        System.arraycopy(xx[0], 0, ww[0], 0, 3);
        ww[0][0] = - ww[0][0];

        System.out.println(Arrays.deepToString(xx));
        System.out.println(Arrays.deepToString(zz));
        System.out.println(Arrays.deepToString(ww));
    }

}
