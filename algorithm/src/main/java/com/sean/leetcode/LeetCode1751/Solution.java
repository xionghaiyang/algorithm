package com.sean.leetcode.LeetCode1751;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-08 06:05
 * @Description https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii
 * 1751. 最多可以参加的会议数目 II
 * 给你一个 events 数组，其中 events[i] = [startDayi, endDayi, valuei] ，表示第 i 个会议在 startDayi 天开始，第 endDayi 天结束，
 * 如果你参加这个会议，你能得到价值 valuei 。
 * 同时给你一个整数 k 表示你能参加的最多会议数目。
 * 你同一时间只能参加一个会议。
 * 如果你选择参加某个会议，那么你必须 完整 地参加完这个会议。
 * 会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与另一个结束日期相同的两个会议。
 * 请你返回能得到的会议价值 最大和 。
 * 1 <= k <= events.length
 * 1 <= k * events.length <= 10^6
 * 1 <= startDayi <= endDayi <= 10^9
 * 1 <= valuei <= 10^6
 */
public class Solution {

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        //dp[i][j]表示前i个会议中选择参加j个会议的最大价值和
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            int p = binarySearch(events, i, events[i][0]);
            for (int j = 1; j <= k; j++) {
                dp[i + 1][j] = Math.max(dp[i][j], dp[p][j - 1] + events[i][2]);
            }
        }
        return dp[n][k];
    }

    private int binarySearch(int[][] events, int right, int target) {
        int left = 0;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (events[mid][1] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
