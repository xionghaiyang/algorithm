package com.sean.leetcode.LeetCode731;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author xionghaiyang
 * @Date 2025-01-03 20:49
 * @Description TODO
 */
public class MyCalendarTwo1 {

    TreeMap<Integer, Integer> cnt;

    public MyCalendarTwo1() {
        cnt = new TreeMap<Integer, Integer>();
    }

    public boolean book(int start, int end) {
        int maxBook = 0;
        cnt.put(start, cnt.getOrDefault(start, 0) + 1);
        cnt.put(end, cnt.getOrDefault(end, 0) - 1);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int freq = entry.getValue();
            maxBook += freq;
            if (maxBook > 2) {
                cnt.put(start, cnt.getOrDefault(start, 0) - 1);
                cnt.put(end, cnt.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }

}
