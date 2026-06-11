package com.dsa.iterator.problems;

import java.util.LinkedList;
import java.util.Queue;

/**What if millions of hits per second?*/
public class LC362HitCounterFollowUp {
    int[] times;
    int[] hits;
    public LC362HitCounterFollowUp(){
        this.times=new int[300];
        this.hits=new int[300];
    }

//    I use a circular array of size 300. Each index represents a second.
//    I reuse slots using modulo and reset stale entries by checking timestamps.

    public void hit(int timestamp) {
       int t = timestamp %300;
       if(times[t]!=timestamp){
           times[t]=timestamp;
           hits[t]=1;
       }else{
           hits[t]++;
       }
    }

    public int getHits(int timestamp) {
        int count=0;
        for(int i=0;i<300;i++){
            if(times[i]-timestamp<300){
                count+=hits[i];
            }
        }
        return count;
    }

    }
