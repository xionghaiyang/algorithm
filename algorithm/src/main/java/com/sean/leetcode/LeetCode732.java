package com.sean.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode732 {

    class MyCalendarThree {

        private TreeMap<Integer, Integer> cnt;

        public MyCalendarThree() {
            cnt = new TreeMap<>();
        }

        public int book(int start, int end) {
            int res = 0;
            int maxBook = 0;
            cnt.put(start, cnt.getOrDefault(start, 0) + 1);
            cnt.put(end, cnt.getOrDefault(end, 0) - 1);
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                maxBook += entry.getValue();
                res = Math.max(maxBook, res);
            }
            return res;
        }
    }

}
