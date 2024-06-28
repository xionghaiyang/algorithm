package com.sean.leetcode.LeetCode100311;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-02 20:11
 * @Description https://leetcode.cn/problems/count-days-without-meetings/
 * 100311. 无需开会的工作日
 * 给你一个正整数 days，表示员工可工作的总天数（从第 1 天开始）。
 * 另给你一个二维数组 meetings，长度为 n，其中 meetings[i] = [start_i, end_i] 表示第 i 次会议的开始和结束天数（包含首尾）。
 * 返回员工可工作且没有安排会议的天数。
 * 注意：会议时间可能会有重叠。
 */
public class Solution {

    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int left = meetings[0][0], right = meetings[0][1], pre = 0, sum = right - left + 1;
        for (int i = 1; i < n; i++) {
            if (meetings[i][1] < right) {
                continue;
            }
            if (meetings[i][0] == left || meetings[i][0] <= right) {
                right = meetings[i][1];
                sum = pre + right - left + 1;
            } else {
                left = meetings[i][0];
                right = meetings[i][1];
                pre = sum;
                sum += right - left + 1;
            }
        }
        return days - sum;
    }

}
