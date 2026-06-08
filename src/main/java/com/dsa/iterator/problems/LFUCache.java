package com.dsa.iterator.problems;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    public class Node{
        int k,v;
        Node prev,next;
        int f;
        public Node(int k,int v){
            this.k=k;this.v=v;
            this.prev=this.next=null;
            this.f=0;
        }
    }
    public class DLL{
        Node head;
        Node tail;
        int size;
        public DLL(){
            this.size=0;
            this.head=new Node(-1,-1);
            this.tail=new Node(-1,-1);
            head.next=tail;
            tail.prev=head;
        }

        void insert(Node node){
            Node p = head;
            Node n = head.next;
            p.next=node;
            node.prev=p;

            n.prev=node;
            node.next=n;
            size++;
        }
        void delete(Node node){
            node.prev.next=node.next;
            node.next.prev=node.prev;
            size--;
        }

        Node deleteLast(){
            Node node = tail.prev;
            delete(node);
            return node;
        }

    }

    Map<Integer,DLL> freqMap;
    Map<Integer,Node> keyValueMap;
    int cap;
    int minFreq;
    public LFUCache(int capacity) {
        this.freqMap=new HashMap<>();
        this.keyValueMap=new HashMap<>();
        this.cap=capacity;
        this.minFreq=0;
    }

    public int get(int key) {
        if(!keyValueMap.containsKey(key)){
            return -1;
        }
        Node node = keyValueMap.get(key);
        update(node);
        return node.v;
    }

    public void put(int key, int value) {
        if(this.cap==0) return;

        if(keyValueMap.containsKey(key)){
            Node node=keyValueMap.get(key);
            node.v=value;
            update(node);
        }else{
            if(keyValueMap.size()==cap){
                DLL dl = freqMap.get(minFreq);
                Node deleted = dl.deleteLast();
                keyValueMap.remove(deleted.k);
            }
            Node node=new Node(key,value);
            keyValueMap.put(key,node);
            minFreq=0;
            freqMap.putIfAbsent(minFreq,new DLL());
            DLL dl = freqMap.get(minFreq);
            dl.insert(node);
        }

    }


    private void update(Node node){
        // get its freq
        int nf=node.f;
        // then remove it from freqMap
        DLL lst = freqMap.get(nf);
        lst.delete(node);
        // verify that nf==minFreq and list size ==0, then update minFreq
        if(nf==minFreq && lst.size==0){
            minFreq=minFreq+1;
        }
        // then freq+1
        nf++;
        node.f++;
        // then add to new freqMap key again

        freqMap.putIfAbsent(nf,new DLL());
        DLL dl = freqMap.get(nf);
        dl.insert(node);
    }


}
