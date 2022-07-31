package com.sean.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode933 {

    public class RecentCounter {

        private Queue<Integer> queue = null;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.add(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

}
