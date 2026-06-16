package com.dsa.dp.decisionmaking;

public class lc121besttimetobuyandsellstock {
    class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit=0;
            int buy=Integer.MAX_VALUE;
            for(int i=0;i<prices.length;i++){
                int sell = prices[i];
                if(sell<buy){
                    buy=sell;
                }else{
                    maxProfit=Math.max(maxProfit,sell-buy);
                }
            }
            return maxProfit;
        }
    }
}
