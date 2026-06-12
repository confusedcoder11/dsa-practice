package com.dsa.iterator.problems;

import java.util.List;

import java.util.*;

public class ZigzagIteratorFollowup {
    Queue<int[]> queue;
    List<List<Integer>> lists;

    public ZigzagIteratorFollowup(List<List<Integer>> lists) {
        this.lists = lists;
        queue = new LinkedList<>();

        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                queue.offer(new int[]{i, 0});
            }
        }
    }

    public int next() {
        int[] curr = queue.poll();
        int listIdx = curr[0];
        int elemIdx = curr[1];

        int val = lists.get(listIdx).get(elemIdx);

        if (elemIdx + 1 < lists.get(listIdx).size()) {
            queue.offer(new int[]{listIdx, elemIdx + 1});
        }

        return val;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
