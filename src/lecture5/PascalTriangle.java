package lecture5;

import java.util.Arrays;

/* from CS61B 2006 Lecture5 Iteration and Arrays */
public class PascalTriangle {
    public static int[][] pascalTriangle(int n) {
        /* 2D Array: an array of references to 1D Arrays */
        int[][] pt = new int[n][]; // index from 0 to n - 1
        for (int i = 0; i < n; i += 1) {
            pt[i] = new int[i + 1]; // row i will have (i + 1) items, index from 0 to i
            pt[i][0] = 1;
            for (int j = 1; j < i; j += 1) {
                pt[i][j] = pt[i-1][j-1] + pt[i-1][j];
            }
            pt[i][i] = 1;
        }
        return pt;
    }

    public static void main(String[] args) {
        int[][] pt = pascalTriangle(6);
        System.out.println(Arrays.deepToString(pt));
    }
}
