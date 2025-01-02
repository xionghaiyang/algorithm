package com.sean.leetcode.LeetCode729;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-01-02 18:49
 * @Description https://leetcode.cn/problems/my-calendar-i/
 * 729. 我的日程安排表 I
 * 实现一个 MyCalendar 类来存放你的日程安排。
 * 如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * 日程可以用一对整数 startTime 和 endTime 表示，这里的时间是半开区间，即 [startTime, endTime),
 * 实数 x 的范围为，  startTime <= x < endTime 。
 * 实现 MyCalendar 类：
 * MyCalendar() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 */
public class MyCalendar {

    List<int[]> booked;

    public MyCalendar() {
        booked = new ArrayList<int[]>();
    }

    public boolean book(int start, int end) {
        for (int[] arr : booked) {
            int left = arr[0], right = arr[1];
            if (left < end && start < right) {
                return false;
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }

}
