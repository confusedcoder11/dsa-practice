package com.dsa.iterator.problems;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinderInDataStream {


    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // max heap
    PriorityQueue<Integer> large = new PriorityQueue<>(); // min heap
    int size=0;
    // small=large | large+1
    public MedianFinderInDataStream() {

    }

    public void addNum(int num) {
        if(small.isEmpty() || num<=small.peek()){
            small.offer(num);
        }else{
            large.offer(num);
        }
        size++;
        balance();
    }

    private void balance(){
        if(small.size()>large.size()+1){
            large.offer(small.poll());
        }else if(small.size()<large.size()){
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if(size%2==1){
            return (double)(small.peek());
        }else{
            return ((double)(small.peek()) + (double)(large.peek())) / 2.0;
        }
    }
}
