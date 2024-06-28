package com.sean.leetcode.LeetCode2446;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-17 08:16
 * @Description: https://leetcode.cn/problems/determine-if-two-events-have-conflict/
 * 2446. 判断两个事件是否存在冲突
 * 给你两个字符串数组 event1 和 event2 ，表示发生在同一天的两个闭区间时间段事件，其中：
 * event1 = [startTime1, endTime1] 且
 * event2 = [startTime2, endTime2]
 * 事件的时间为有效的 24 小时制且按 HH:MM 格式给出。
 * 当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 冲突 。
 * 如果两个事件之间存在冲突，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean haveConflict(String[] event1, String[] event2) {
        return !(event1[1].compareTo(event2[0]) < 0 || event2[1].compareTo(event1[0]) < 0);
    }

}
