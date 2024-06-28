package com.sean.leetcode.LeetCode2409;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-17 08:07
 * @Description: https://leetcode.cn/problems/count-days-spent-together/
 * 2409. 统计共同度过的日子数
 * Alice 和 Bob 计划分别去罗马开会。
 * 给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob 。
 * Alice 会在日期 arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），
 * 而 Bob 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。
 * 每个字符串都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
 * 请你返回 Alice和 Bob 同时在罗马的天数。
 * 你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] 。
 */
public class Solution {

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] preSum = new int[13];
        for (int i = 0; i < 12; i++) {
            preSum[i + 1] = preSum[i] + days[i];
        }
        int arriveAliceDay = getDay(arriveAlice, preSum);
        int leaveAliceDay = getDay(leaveAlice, preSum);
        int arriveBobDay = getDay(arriveBob, preSum);
        int leaveBobDay = getDay(leaveBob, preSum);
        return Math.max(0, Math.min(leaveAliceDay, leaveBobDay) - Math.max(arriveAliceDay, arriveBobDay) + 1);
    }

    private int getDay(String day, int[] preSum) {
        int month = Integer.parseInt(day.substring(0, 2));
        int date = Integer.parseInt(day.substring(3));
        return preSum[month - 1] + date;
    }

}
