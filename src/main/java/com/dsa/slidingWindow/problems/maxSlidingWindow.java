package com.dsa.slidingWindow.problems;

import java.util.LinkedList;
import java.util.List;

public class maxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        List<Integer> q = new LinkedList<>();

        for(int i=0;i<Math.min(n,k);i++){
            while(!q.isEmpty() && nums[q.getLast()]<nums[i]){
                q.removeLast();
            }
            q.addLast(i);
        }

        int[] result=new int[n-k+1];

        int index=0;
        result[index++]=nums[q.getFirst()];

        for(int i=k;i<n;i++){
            while(!q.isEmpty() && q.getFirst()<=i-k){
                q.removeFirst();
            }
            while(!q.isEmpty() && nums[q.getLast()]<nums[i]){
                q.removeLast();
            }
            q.addLast(i);
            result[index++]=nums[q.getFirst()];
        }

        return result;
    }
}
