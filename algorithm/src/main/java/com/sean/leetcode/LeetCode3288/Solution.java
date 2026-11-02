package com.sean.leetcode.LeetCode3288;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-02 17:45
 * @Description: https://leetcode.cn/problems/length-of-the-longest-increasing-path
 * 3288. 最长上升路径的长度
 * 给你一个长度为 n 的二维整数数组 coordinates 和一个整数 k ，其中 0 <= k < n 。
 * coordinates[i] = [xi, yi] 表示二维平面里一个点 (xi, yi) 。
 * 如果一个点序列 (x1, y1), (x2, y2), (x3, y3), ..., (xm, ym) 满足以下条件，那么我们称它是一个长度为 m 的 上升序列 ：
 * 对于所有满足 1 <= i < m 的 i 都有 xi < xi + 1 且 yi < yi + 1 。
 * 对于所有 1 <= i <= m 的 i 对应的点 (xi, yi) 都在给定的坐标数组里。
 * 请你返回包含坐标 coordinates[k] 的 最长上升路径 的长度。
 * 1 <= n == coordinates.length <= 10^5
 * coordinates[i].length == 2
 * 0 <= coordinates[i][0], coordinates[i][1] <= 10^9
 * coordinates 中的元素 互不相同 。
 * 0 <= k <= n - 1
 */
public class Solution {

    public int maxPathLength(int[][] coordinates, int k) {
        int kx = coordinates[k][0], ky = coordinates[k][1];
        Arrays.sort(coordinates, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] f = new int[coordinates.length + 1];
        int len = 0;
        for (int[] coordinate : coordinates) {
            int x = coordinate[0], y = coordinate[1];
            if ((x < kx && y < ky) || (x > kx && y > ky)) {
                if (len == 0 || y > f[len]) {
                    f[++len] = y;
                } else {
                    int left = 1, right = len, j = 0;
                    while (left <= right) {
                        int mid = left + ((right - left) >> 1);
                        if (f[mid] < y) {
                            j = mid;
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                    f[j + 1] = y;
                }
            }
        }
        return len + 1;
    }

}
