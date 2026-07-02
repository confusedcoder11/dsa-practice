package com.dsa.dp.countways;

public class LC1223DiceRollSimulation {
    class Solution {
        private final int MOD = 1_000_000_007;
        Integer[][][] dp = new Integer[5001][7][16];
        public int dieSimulator(int n, int[] rollMax) {
            return helper(n,0,0,rollMax);
        }
        int helper(int n,int prev,int consCount,int[] rollMax){
            if(n==0) return 1;

            if(dp[n][prev][consCount]!=null) return dp[n][prev][consCount];

            int ways = 0;
            for(int i=1;i<=6;i++){
                int curr=i;
                int mxCond = rollMax[curr-1];
                if(prev==curr){
                    int currCount=consCount+1;
                    if(currCount<=mxCond){
                        ways = (ways+helper(n-1,curr,currCount,rollMax))%MOD;
                    }
                }else{
                    ways = (ways+helper(n-1,curr,1,rollMax))%MOD;
                }
            }
            dp[n][prev][consCount]=ways;
            return ways;
        }
    }
}
