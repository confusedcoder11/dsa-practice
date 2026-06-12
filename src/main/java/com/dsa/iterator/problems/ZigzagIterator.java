package com.dsa.iterator.problems;

import java.util.List;

public class ZigzagIterator {
    int i=0;int j=0;boolean turn1=true;
    List<Integer> v1;
    List<Integer> v2;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2){
        this.v1=v1;
        this.v2=v2;
    }
//    v2 = [1]
//    v1 = [3, 4, 5, 6]
    public int next(){
        if((turn1 && i<v1.size()) || j>=v2.size()){
            this.turn1=false;
            return v1.get(i++);
        }else{
            this.turn1=true;
            return v2.get(j++);
        }
    }

    public boolean hasNext(){
        return i<v1.size() ||  j<v2.size();
    }
}
