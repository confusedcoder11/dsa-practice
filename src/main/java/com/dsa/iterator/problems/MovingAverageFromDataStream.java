package com.dsa.iterator.problems;

import javax.sound.sampled.Line;
import java.util.*;

// Sliding window + queue
//  Moving Average from Data Stream
//LeetCode 346
public class MovingAverageFromDataStream {
    Queue<Integer> queue;
    int size;
    int sum;

    public MovingAverageFromDataStream(int k){
        this.size=k;
        this.sum=0;
        this.queue=new LinkedList<>();
    }

    public double next(int val){
        // Step 1: add new value
        queue.offer(val);
        sum+=val;

        // Step 2: remove oldest if size exceeded
        if(queue.size()>size){
            sum-=queue.poll();
        }
        // Step 3: return avg
        return (double)(sum) / queue.size();
    }
}
