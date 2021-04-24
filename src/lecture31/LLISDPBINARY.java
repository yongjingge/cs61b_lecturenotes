package lecture31;

import java.util.Arrays;

/* Leetcode #300 Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * Approach: Dynamic Programming & Binary Search
 *  */
public class LLISDPBINARY {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        for (int n : nums) {
            /* binarySearch method returns index of the search key,
            * in this case n in the for-each loop.
            * If n is contained in the array, it returns its index;
            * otherwise (-(insertion point) - 1). */
            int index = Arrays.binarySearch(dp, 0, res, n);
            if (index < 0) {
                index = - (index + 1);
            }
            dp[index] = n;
            if (index == res) {
                res += 1;
            }
        }
        return res;
    }
}
