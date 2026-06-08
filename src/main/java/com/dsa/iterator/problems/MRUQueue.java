package com.dsa.iterator.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MRUQueue {

    List<LinkedList<Integer>> buckets;
    int bucketSize;

    public MRUQueue(int n) {
        this.bucketSize = (int) (Math.sqrt(n));
        this.buckets = new ArrayList<>();

        int val = 1;
        while (val <= n) {
            LinkedList<Integer> bucket = new LinkedList<>();
            for (int i = 0; i < bucketSize && val <= n; i++) {
                bucket.add(val++);
            }
            buckets.add(bucket);
        }
    }

    public int fetch(int k) {
        int idx = k - 1;

        //step 1 - find the bucket
        for (int i = 0; i < buckets.size(); i++) {
            LinkedList<Integer> bucket = buckets.get(i);
            if (idx < bucket.size()) {
                int val = bucket.remove(idx);
                //step 2 - rebalances the buckets
                for (int j = i + 1; j < buckets.size(); j++) {
                    bucket.add(buckets.get(j).removeFirst());
                    bucket = buckets.get(j);
                }
                // step 3 - add to last bucket
                buckets.get(buckets.size() - 1).addLast(val);
                return val;
            } else {
                idx -= bucket.size();
            }
        }
        return -1;
    }
}