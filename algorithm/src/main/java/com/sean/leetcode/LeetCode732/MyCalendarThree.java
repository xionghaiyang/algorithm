package com.sean.leetcode.LeetCode732;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author xionghaiyang
 * @Date 2025-01-04 20:55
 * @Description https://leetcode.cn/problems/my-calendar-iii/
 * 732. 我的日程安排表 III
 * 当 k 个日程存在一些非空交集时（即, k 个日程包含了一些相同时间），就会产生 k 次预订。
 * 给你一些日程安排 [startTime, endTime) ，请你在每个日程安排添加后，返回一个整数 k ，
 * 表示所有先前日程安排会产生的最大 k 次预订。
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 * MyCalendarThree() 初始化对象。
 * int book(int startTime, int endTime) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 * 0 <= startTime < endTime <= 10^9
 * 每个测试用例，调用 book 函数最多不超过 400次
 */
public class MyCalendarThree {

    TreeMap<Integer, Integer> cnt;

    public MyCalendarThree() {
        cnt = new TreeMap<Integer, Integer>();
    }

    public int book(int start, int end) {
        int res = 0;
        int maxBook = 0;
        cnt.put(start, cnt.getOrDefault(start, 0) + 1);
        cnt.put(end, cnt.getOrDefault(end, 0) - 1);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int freq = entry.getValue();
            maxBook += freq;
            res = Math.max(maxBook, res);
        }
        return res;
    }

}
