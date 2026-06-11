package com.dsa.iterator.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
* If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
* */
public class MedianFinderInDataStreamFollowUp1 {


    public MedianFinderInDataStreamFollowUp1() {

    }
    int[] freq = new int[101];
    int size = 0;

    public void addNum(int num) {
        freq[num]++;
        size++;
    }

    public double findMedian() {
        int count = 0;

        int k1 = (size + 1) / 2;
        int k2 = (size + 2) / 2;

        int m1 = -1, m2 = -1;

        for (int i = 0; i <= 100; i++) {
            count += freq[i];

            if (count >= k1 && m1 == -1) {
                m1 = i;
            }

            if (count >= k2) {
                m2 = i;
                break;
            }
        }

        return (m1 + m2) / 2.0;
    }
}
