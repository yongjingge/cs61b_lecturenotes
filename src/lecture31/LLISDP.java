package lecture31;

/* Leetcode #300 Longest Increasing Subsequence
* https://leetcode.com/problems/longest-increasing-subsequence/
* Approach: Dynamic Programming (DAGless)
*  */
public class LLISDP {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        int max = 1;
        for (int i = 0; i < len; i += 1) {
            dp[i] = 1;
            for (int j = 0; j < i; j += 1) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
