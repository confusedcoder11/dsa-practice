package com.dsa.dp.decisionmaking;

/**
 You are given an integer array prices
 where prices[i] is the price of a given stock on the ith day, and an integer k.
 Find the maximum profit you can achieve.
 You may complete at most k transactions: i.e.
 you may buy at most k times and sell at most k times.
 Note: You may not engage in multiple transactions simultaneously
 (i.e., you must sell the stock before you buy again).
 */
public class LC188BestTimetoBuyandSellStockIV {
    class Solution {
        public int maxProfit(int K, int[] prices) {
            int n=prices.length;
            int[][] history=new int[K+1][n];
            int profit=0;
            for(int k=1;k<=K;k++){
                int best = -prices[0];

                history[k][0]=0;

                for(int i=1;i<n;i++){

                    // option 2: don't sell today
                    // option 1: sell today using best previous buy
                    history[k][i]=Math.max(
                            prices[i]+best
                            ,history[k][i-1]
                    );
                    // update best "buy state"
                    best=Math.max(best,
                            history[k-1][i]-prices[i]);

                }
                profit=Math.max(profit,history[k][n-1]);
            }
            return profit;
        }
    }
}
