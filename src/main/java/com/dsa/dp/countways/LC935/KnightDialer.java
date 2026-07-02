package com.dsa.dp.countways.LC935;

/**
 The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
 or two squares horizontally and one square vertically (with both forming the shape of an L).
 The possible movements of chess knight are shown in this diagram:
 A chess knight can move as indicated in the chess diagram below:
 * */
public class KnightDialer {
    class Solution {
        private final int MOD = 1000000007;
        int[][] dirs = {{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
        public int knightDialer(int n) {
            // choose first one
            Integer[][][] dp = new Integer[n][4][3];
            int ways=0;
            for(int i=0;i<4;i++){
                for(int j=0;j<3;j++){
                    if(i!=3 || (i==3 && j==1)){
                        ways=(ways+helper(n-1,i,j,dp))%MOD;
                    }
                }
            }
            return ways;
        }
        private int helper(int n, int posR, int posC,Integer[][][] dp){
            if(n==0) return 1;
            if(dp[n][posR][posC]!=null){
                return dp[n][posR][posC];
            }
            int ways=0;
            for(int[] dir : dirs){
                int dr=dir[0],dc=dir[1];
                int r=posR+dr;
                int c=posC+dc;
                if(isValid(r,c)){
                    ways=(ways+helper(n-1,r,c,dp))%MOD;
                }
            }
            dp[n][posR][posC]=ways;
            return ways;
        }
        private boolean isValid(int r, int c){
            if((r>=0 && c>=0 && c<3 && r<3 )|| (r==3 && (c==1))){
                return true;
            }
            return false;
        }
    }
}
