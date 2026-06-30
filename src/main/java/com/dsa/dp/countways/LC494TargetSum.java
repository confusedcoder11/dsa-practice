package com.dsa.dp.countways;

import java.util.HashMap;
import java.util.Map;

public class LC494TargetSum {
    class Solution {
        Map<String,Integer> memo=new HashMap<>();

        public int findTargetSumWays(int[] nums, int target) {
            // each num have option of + or -
            int n=nums.length;
            // what is my state -> dp[target][index] -> dp[t][n-1];
            // from where i am coming -> dp[t][i] = dp[t+num][i-1] + dp[t-num][i-1];
            // base condition
            return helper(nums,0,0,target);
        }
        int helper(int[] nums,int index,int currSum,int target){
            if(index==nums.length){
                return currSum==target ? 1 : 0;
            }
            String key = index+","+currSum;
            if(memo.containsKey(key)){
                return memo.get(key);
            }
            int ways = helper(nums,index+1,currSum+nums[index],target)
                    +helper(nums,index+1,currSum-nums[index],target);

            memo.put(key,ways);

            return ways;
        }
    }

// 1 2 3 -> 3
}
