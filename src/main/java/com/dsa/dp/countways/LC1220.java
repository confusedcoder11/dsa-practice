package com.dsa.dp.countways;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

 Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 Each vowel 'a' may only be followed by an 'e'.
 Each vowel 'e' may only be followed by an 'a' or an 'i'.
 Each vowel 'i' may not be followed by another 'i'.
 Each vowel 'o' may only be followed by an 'i' or a 'u'.
 Each vowel 'u' may only be followed by an 'a'.
 Since the answer may be too large, return it modulo 10^9 + 7.
 * */
public class LC1220 {
    class Solution {
        private final int MOD = 1_000_000_007;



        public int countVowelPermutation(int n) {
            Map<Character, Set<Character>> vowelToNext=new HashMap<>();
            vowelToNext.put('a',new HashSet<>(Set.of('e')));
            vowelToNext.put('e',new HashSet<>(Set.of('a','i')));
            vowelToNext.put('i',new HashSet<>(Set.of('a','e','o','u')));
            vowelToNext.put('o',new HashSet<>(Set.of('i','u')));
            vowelToNext.put('u',new HashSet<>(Set.of('a')));
            HashMap<String,Integer> dp =new HashMap<>();
            return helper(n,'x',vowelToNext,dp);
        }
        int helper(int n, char prev,Map<Character, Set<Character>> vowelToNext,HashMap<String,Integer> dp){
            if(n==0) return 1;
            String key = n+"-"+prev;
            if(dp.containsKey(key)) return dp.get(key);
            int ways = 0;
            if(prev=='x'){
                // this is start
                for(char ch : vowelToNext.keySet()){
                    ways = (ways + helper(n-1,ch,vowelToNext,dp))%MOD;
                }
            }else{
                for(char ch : vowelToNext.get(prev)){
                    ways = (ways + helper(n-1,ch,vowelToNext,dp))%MOD;
                }
            }
            dp.put(key,ways);
            return ways;
        }
    }
}
