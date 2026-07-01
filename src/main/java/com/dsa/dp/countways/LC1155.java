package com.dsa.dp.countways;

import java.util.HashMap;
import java.util.Map;
/**
 1155. Number of Dice Rolls With Target Sum
 Solved
 Medium
 Topics
 premium lock icon
 Companies
 Hint
 You have n dice, and each dice has k faces numbered from 1 to k.

 Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice,
 so the sum of the face-up numbers equals target.
 Since the answer may be too large, return it modulo 109 + 7.
 * */
public class LC1155 {
    class Solution {
        private final int MOD = 1000000007;
        Map<String, Integer> memo = new HashMap<>();

        public int numRollsToTarget(int n, int K, int target) {
            // state - dp[t][n] += dp[t-k][n-1] (for all k);
            // derivation
            // base

            if (target == 0 && n == 0)
                return 1;
            if (target < 0 || n < 0)
                return 0;
            String key = n + "," + target;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }
            int ways = 0;
            for (int k = 1; k <= K; k++) {
                ways = (ways + numRollsToTarget(n - 1, K, target - k)) % MOD;
            }
            memo.put(key, ways);
            return ways;
        }
    }
}
