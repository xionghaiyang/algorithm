package com.sean.leetcode.LeetCode3464;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-25 06:37
 * @Description https://leetcode.cn/problems/maximize-the-distance-between-points-on-a-square
 * 3464. 正方形上的点之间的最大距离
 * 给你一个整数 side，表示一个正方形的边长，正方形的四个角分别位于笛卡尔平面的 (0, 0) ，(0, side) ，(side, 0) 和 (side, side) 处。
 * 同时给你一个 正整数 k 和一个二维整数数组 points，其中 points[i] = [xi, yi] 表示一个点在正方形边界上的坐标。
 * 你需要从 points 中选择 k 个元素，使得任意两个点之间的 最小 曼哈顿距离 最大化 。
 * 返回选定的 k 个点之间的 最小 曼哈顿距离的 最大 可能值。
 * 两个点 (xi, yi) 和 (xj, yj) 之间的曼哈顿距离为 |xi - xj| + |yi - yj|。
 * 1 <= side <= 10^9
 * 4 <= points.length <= min(4 * side, 15 * 10^3)
 * points[i] == [xi, yi]
 * 输入产生方式如下：
 * points[i] 位于正方形的边界上。
 * 所有 points[i] 都 互不相同 。
 * 4 <= k <= min(25, points.length)
 */
public class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (x == 0) {
                arr[i] = y;
            } else if (y == side) {
                arr[i] = (long) side + x;
            } else if (x == side) {
                arr[i] = 3L * side - y;
            } else {
                arr[i] = 4L * side - x;
            }
        }
        Arrays.sort(arr);
        int res = 0;
        int left = 1, right = (int) (side * 4L / k) + 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(arr, side, k, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean check(long[] arr, int side, int k, long low) {
        int n = arr.length;
        int[] index = new int[k];
        long cur = arr[0];
        for (int j = 1; j < k; j++) {
            int i = binarySearch(arr, cur + low);
            if (i == -1) {
                return false;
            }
            index[j] = i;
            cur = arr[i];
        }
        if (cur - arr[0] <= 4L * side - low) {
            return true;
        }
        int end0 = index[1];
        for (index[0] = 1; index[0] < end0; index[0]++) {
            for (int j = 1; j < k; j++) {
                while (arr[index[j]] < arr[index[j - 1]] + low) {
                    index[j]++;
                    if (index[j] == n) {
                        return false;
                    }
                }
            }
            if (arr[index[k - 1]] - arr[index[0]] <= 4L * side - low) {
                return true;
            }
        }
        return false;
    }

    private int binarySearch(long[] arr, long target) {
        int res = -1;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
