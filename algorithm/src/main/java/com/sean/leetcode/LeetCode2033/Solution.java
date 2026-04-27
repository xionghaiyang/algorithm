package com.sean.leetcode.LeetCode2033;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-28 06:38
 * @Description https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid
 * 2033. 获取单值网格的最小操作数
 * 给你一个大小为 m x n 的二维整数网格 grid 和一个整数 x 。
 * 每一次操作，你可以对 grid 中的任一元素 加 x 或 减 x 。
 * 单值网格 是全部元素都相等的网格。
 * 返回使网格化为单值网格所需的 最小 操作数。
 * 如果不能，返回 -1 。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 1 <= x, grid[i][j] <= 10^4
 */
public class Solution {

    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length, k = m * n;
        int[] arr = new int[k];
        int index = 0;
        int target = grid[0][0] % x;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                if (v % x != target) {
                    return -1;
                }
                arr[index++] = v;
            }
        }
        Arrays.sort(arr);
        int median = arr[k / 2];
        int res = 0;
        for (int v : arr) {
            res += Math.abs(v - median);
        }
        return res / x;
    }

}
