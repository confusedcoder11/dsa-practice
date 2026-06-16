package com.dsa.iterator.problems;

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer nextElement;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator=iterator;
        if(iterator.hasNext()){
            this.nextElement=iterator.next();
        }

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer result = this.nextElement;
        if(iterator.hasNext()){
            this.nextElement=iterator.next();
        }else{
            this.nextElement=null;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return nextElement!=null;
    }
}
