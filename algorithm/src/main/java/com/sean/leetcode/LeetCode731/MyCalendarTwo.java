package com.sean.leetcode.LeetCode731;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-01-03 20:13
 * @Description https://leetcode.cn/problems/my-calendar-ii/
 * 731. 我的日程安排表 II
 * 实现一个程序来存放你的日程安排。
 * 如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 三重预订。
 * 事件能够用一对整数 startTime 和 endTime 表示，在一个半开区间的时间 [startTime, endTime) 上预定。
 * 实数 x 的范围为  startTime <= x < endTime。
 * 实现 MyCalendarTwo 类：
 * MyCalendarTwo() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 * 0 <= start < end <= 10^9
 * 最多调用 book 1000 次。
 */
public class MyCalendarTwo {

    List<int[]> booked;
    List<int[]> overlaps;

    public MyCalendarTwo() {
        booked = new ArrayList<int[]>();
        overlaps = new ArrayList<int[]>();
    }

    public boolean book(int start, int end) {
        for (int[] overlap : overlaps) {
            int left = overlap[0];
            int right = overlap[1];
            if (left < end && start < right) {
                return false;
            }
        }
        for (int[] book : booked) {
            int left = book[0];
            int right = book[1];
            if (left < end && start < right) {
                overlaps.add(new int[]{Math.max(left, start), Math.min(right, end)});
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }

}
