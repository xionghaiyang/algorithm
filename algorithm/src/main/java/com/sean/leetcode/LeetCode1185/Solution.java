package com.sean.leetcode.LeetCode1185;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-30 13:28
 * @Description: https://leetcode.cn/problems/day-of-the-week/
 * 1185. 一周中的第几天
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 */
public class Solution {

    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        int res = 4;
        for (int i = 1971; i < year; i++) {
            if (i % 400 == 0 || (i % 4 == 0 && i % 100 != 0)) {
                res += 366;
            } else {
                res += 365;
            }
        }
        for (int i = 0; i < month - 1; i++) {
            res += monthDays[i];
        }
        if (month > 2 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            res += 1 + day;
        } else {
            res += day;
        }
        return week[res % 7];
    }

}
