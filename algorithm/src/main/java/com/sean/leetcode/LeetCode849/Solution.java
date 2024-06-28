package com.sean.leetcode.LeetCode849;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-08-22 15:15
 * @Description: https://leetcode.cn/problems/maximize-distance-to-closest-person/
 * 849. 到最近的人的最大距离
 * 给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，
 * seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
 * 至少有一个空座位，且至少有一人已经坐在座位上。
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * 返回他到离他最近的人的最大距离。
 */
public class Solution {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int res = 0;
        int left = 0;
        while (left < n && seats[left] == 0) {
            left++;
        }
        res = Math.max(res, left);
        while (left < n) {
            int right = left + 1;
            while (right < n && seats[right] == 0) {
                right++;
            }
            if (right == n) {
                res = Math.max(res, right - left - 1);
            } else {
                res = Math.max(res, (right - left) / 2);
            }
            left = right;
        }
        return res;
    }

}
