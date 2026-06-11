package com.dsa.iterator.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
public class MedianFinderInDataStreamFollowUp2 {


    public MedianFinderInDataStreamFollowUp2() {

    }
    // middle range
    int[] freq = new int[101];
    int midCount = 0;

    // outliers
    PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // < 0
    PriorityQueue<Integer> right = new PriorityQueue<>(); // > 100

    int total = 0;

    public void addNum(int num) {
        if (num >= 0 && num <= 100) {
            freq[num]++;
            midCount++;
        } else if (num < 0) {
            left.offer(num);
        } else {
            right.offer(num);
        }
        total++;
    }

    public double findMedian() {
        int k1 = (total + 1) / 2;
        int k2 = (total + 2) / 2;

        int m1 = getKth(k1);
        int m2 = getKth(k2);

        return (m1 + m2) / 2.0;
    }

    private int getKth(int k) {

        // 1️⃣ Check left heap (smallest elements)
        if (k <= left.size()) {
            return getKthFromHeap(left, k);
        }

        // skip left heap
        k -= left.size();

        // 2️⃣ Check freq array [0..100]
        int count = 0;
        for (int i = 0; i <= 100; i++) {
            count += freq[i];
            if (count >= k) {
                return i;
            }
        }

        // skip middle
        k -= midCount;

        // 3️⃣ Check right heap
        return getKthFromHeap(right, k);
    }

    // helper: get kth element from heap WITHOUT destroying it
    private int getKthFromHeap(PriorityQueue<Integer> heap, int k) {
        List<Integer> temp = new ArrayList<>();

        int val = -1;
        for (int i = 0; i < k; i++) {
            val = heap.poll();
            temp.add(val);
        }

        // restore heap
        for (int num : temp) {
            heap.offer(num);
        }

        return val;
    }
}
