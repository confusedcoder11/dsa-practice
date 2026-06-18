package com.dsa.dp.decisionmaking;

/**
 You are given an array prices where prices[i] is the price of a given stock on the ith day.
 Find the maximum profit you can achieve. You may complete at most two transactions.
 Note: You may not engage in multiple transactions simultaneously
 (i.e., you must sell the stock before you buy again).
 */
public class LC123BestTimetoBuyandSellStockIII {
    class Solution {
        public int maxProfit(int[] prices) {
            // two arrays
            // one - max profit if you do transaction today, or i mean sell
            // second - max profit if you buy today
            int n=prices.length;
            int[] left=new int[n];
            int[] right=new int[n];

            // one
            int min=prices[0];
            left[0]=0;
            for(int i=1;i<n;i++){
                min=Math.min(min,prices[i]);
                left[i]=Math.max(left[i-1], prices[i]-min);
            }
            int max=prices[n-1];
            right[n-1]=0;
            for(int i=n-2;i>=0;i--){
                max=Math.max(max,prices[i]);
                right[i]=Math.max(right[i+1],max-prices[i]);
            }
            int profit=Math.max(left[n-1],right[0]);
            for(int i=1;i<n-1;i++){
                profit=Math.max(profit,left[i]+right[i+1]);
            }
            return profit;
        }
    }
}
