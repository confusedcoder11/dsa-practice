package com.dsa.dp.decisionmaking;

/**
 ⚖️ Why This Is Optimal
     Because:
         Any increasing sequence can be broken into smaller profitable transactions
         And summing them gives the same or better result than one big transaction
 */
public class LC122BestTimetoBuyandSellStockII {
    class Solution {
        public int maxProfit(int[] prices) {
            int profit=0;
            for(int i=1;i<prices.length;i++){
                if(prices[i]>prices[i-1]){
                    profit += prices[i]-prices[i-1];
                }
            }
            return profit;
        }
    }
}
