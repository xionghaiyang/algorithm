package com.sean.leetcode.LeetCode729;

import java.util.TreeSet;

/**
 * @Author xionghaiyang
 * @Date 2025-01-02 19:00
 * @Description TODO
 */
public class MyCalendar1 {

    TreeSet<int[]> booked;

    public MyCalendar1() {
        booked = new TreeSet<int[]>((a, b) -> a[0] - b[0]);
    }

    public boolean book(int start, int end) {
        if (booked.isEmpty()) {
            booked.add(new int[]{start, end});
            return true;
        }
        int[] tmp = {end, 0};
        int[] arr = booked.ceiling(tmp);
        int[] prev = arr == null ? booked.last() : booked.lower(arr);
        if (arr == booked.first() || booked.lower(tmp)[1] <= start) {
            booked.add(new int[]{start, end});
            return true;
        }
        return false;
    }

}
