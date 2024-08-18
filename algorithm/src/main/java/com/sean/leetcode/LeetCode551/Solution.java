package com.sean.leetcode.LeetCode551;

/**
 * @Author xionghaiyang
 * @Date 2024-08-18 08:04
 * @Description https://leetcode.cn/problems/student-attendance-record-i/
 * 551. 学生出勤记录 I
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。
 * 记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean checkRecord(String s) {
        int absent = 0, late = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                absent++;
                if (absent >= 2) {
                    return false;
                }
                late = 0;
            } else if (c == 'L') {
                late++;
                if (late >= 3) {
                    return false;
                }
            } else {
                late = 0;
            }
        }
        return true;
    }

}
