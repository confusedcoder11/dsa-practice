package com.dsa.dp.decisionmaking;

/**
 You are given an array prices where prices[i] is the price of a given stock on the ith day.

 Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

 After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).


 */
public class LC309BestTimetoBuyandSellStockWithCooldown {
    class Solution {
        public int maxProfit(int[] prices) {
            // just cool down :
            // if buying today fine, but wouldn't have sold on i-1
            // if selling today, buy can be last day
            int n=prices.length;
            int[] buyState = new int[n];
            int[] sellState = new int[n];
            buyState[0]=-prices[0];
            sellState[0]=0;

            for(int i=1;i<n;i++){
                buyState[i]=Math.max(buyState[i-1],i-2>=0 ? sellState[i-2]-prices[i] : -prices[i]);
                sellState[i]=Math.max(sellState[i-1],buyState[i-1]+prices[i]);
            }

            return sellState[n-1];

        }
    }
}
