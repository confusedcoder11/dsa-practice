package com.dsa.dp.countways;

public class LC576OutOfBoundaryPaths {
    class Solution {
        private final int MOD=
                1000000007
                ;
        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            // state
            // derivation
            // base path
            if(maxMove<=0) return 0;
            Integer[][][] dp = new Integer[m][n][maxMove+1];
            helper(m,n,maxMove,startRow,startColumn,dp);

            return dp[startRow][startColumn][maxMove];
        }
        int helper(int m, int n, int maxMove, int startRow, int startColumn,Integer[][][] dp){
            if(startRow<0 || startRow>=m || startColumn<0 || startColumn>=n){
                return 1;
            }
            if(maxMove<=0) return 0;
            if(dp[startRow][startColumn][maxMove]!=null) return dp[startRow][startColumn][maxMove];
            int ways = 0;
            ways = (ways+helper(m,n,maxMove-1,startRow+1,startColumn,dp))%MOD;
            ways = (ways+helper(m,n,maxMove-1,startRow-1,startColumn,dp))%MOD;
            ways = (ways+helper(m,n,maxMove-1,startRow,startColumn+1,dp))%MOD;
            ways = (ways+helper(m,n,maxMove-1,startRow,startColumn-1,dp))%MOD;

            dp[startRow][startColumn][maxMove]=ways;
            return ways;
        }
    }
}
