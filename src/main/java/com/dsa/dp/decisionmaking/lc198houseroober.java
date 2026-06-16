package com.dsa.dp.decisionmaking;

public class lc198houseroober {
    class Solution {
        public int rob(int[] nums) {
            int n=nums.length;
            int[][] dp = new int[n][2];
            // 0th not robbed
            // 1th robbed
            dp[0][0]=0;dp[0][1]=nums[0];
            for(int i=1;i<n;i++){
                dp[i][0] = Math.max(dp[i-1][1],dp[i-1][0]); // not robbing this house
                dp[i][1] = dp[i-1][0]+nums[i] ; // robbing this house
            }
            return Math.max(dp[n-1][0],dp[n-1][1]);
        }
    }
}
