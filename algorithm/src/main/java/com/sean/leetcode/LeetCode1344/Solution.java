package com.sean.leetcode.LeetCode1344;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-18 18:42
 * @Description: https://leetcode.cn/problems/angle-between-hands-of-a-clock
 * 1344. 时钟指针的夹角
 * 给你两个数 hour 和 minutes 。
 * 请你返回在时钟上，由给定时间的时针和分针组成的较小角的角度（60 单位制）。
 * 1 <= hour <= 12
 * 0 <= minutes <= 59
 * 与标准答案误差在 10^-5 以内的结果都被视为正确结果。
 */
public class Solution {

    public double angleClock(int hour, int minutes) {
        int oneMinAngle = 6, oneHourAngle = 30;
        double minutesAngle = oneMinAngle * minutes;
        double hourAngle = (hour % 12 + minutes / 60.0) * oneHourAngle;
        double diff = Math.abs(hourAngle - minutesAngle);
        return Math.min(diff, 360 - diff);
    }

}
