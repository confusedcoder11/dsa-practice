package com.dsa.dp.countways;

public class LC63UniquePathII {
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m=obstacleGrid.length;
            int n=obstacleGrid[0].length;

            int[][] dp = new int[m][n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(i==0){
                        dp[i][j]= (obstacleGrid[i][j]==1 || (j-1>=0 && dp[i][j-1]==0)) ? 0 : 1;
                    }else if(j==0){
                        dp[i][j]= (obstacleGrid[i][j]==1 || (i-1>=0 && dp[i-1][j]==0)) ? 0 : 1;
                    } else{
                        int down = (obstacleGrid[i][j]==1 || dp[i-1][j]==0) ? 0 : dp[i-1][j];
                        int right = (obstacleGrid[i][j]==1 || dp[i][j-1]==0) ? 0 : dp[i][j-1];
                        dp[i][j]= down+right;
                    }
                }
            }
            return dp[m-1][n-1];
        }
    }
}
