package com.sean.leetcode.LeetCode2187;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-10-08 14:53
 * @Description https://leetcode.cn/problems/minimum-time-to-complete-trips
 * 2187. 完成旅途的最少时间
 * 给你一个数组 time ，其中 time[i] 表示第 i 辆公交车完成 一趟旅途 所需要花费的时间。
 * 每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始 下一趟旅途。
 * 每辆公交车 独立 运行，也就是说可以同时有多辆公交车在运行且互不影响。
 * 给你一个整数 totalTrips ，表示所有公交车 总共 需要完成的旅途数目。
 * 请你返回完成 至少 totalTrips 趟旅途需要花费的 最少 时间。
 * 1 <= time.length <= 10^5
 * 1 <= time[i], totalTrips <= 10^7
 */
public class Solution {

    public long minimumTime(int[] time, int totalTrips) {
        long left = 1;
        long right = (long) totalTrips * Arrays.stream(time).max().orElse(0);
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (check(mid, time, totalTrips)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //判断t时间内是否可以完成totalTrips躺旅途
    private boolean check(long t, int[] time, int totalTrips) {
        long cnt = 0;
        for (int period : time) {
            cnt += t / period;
        }
        return cnt >= totalTrips;
    }

}
