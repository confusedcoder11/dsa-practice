package com.dsa.iterator.problems;

import java.util.LinkedList;
import java.util.Queue;

public class LC362HitCounter {
    Queue<Integer> counter;
    public LC362HitCounter(){
        this.counter=new LinkedList<>();
    }

    public void hit(int timestamp) {
        counter.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while(!counter.isEmpty() && counter.peek()-300 <= timestamp){
            counter.poll();
        }
        return counter.size();
    }

    }
