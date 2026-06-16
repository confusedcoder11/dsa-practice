package com.dsa.iterator.problems;

import java.util.Iterator;

class PeekingIteratorGenericType<T> implements Iterator<T> {
    Iterator<T> iterator;
    T nextElement;
    public PeekingIteratorGenericType(Iterator<T> iterator) {
        this.iterator=iterator;
        if(iterator.hasNext()){
            this.nextElement=iterator.next();
        }

    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        T result = this.nextElement;
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
