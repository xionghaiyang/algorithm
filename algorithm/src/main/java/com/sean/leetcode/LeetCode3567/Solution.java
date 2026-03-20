package com.sean.leetcode.LeetCode3567;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 10:08
 * @Description https://leetcode.cn/problems/minimum-absolute-difference-in-sliding-submatrix
 * 3567. 子矩阵的最小绝对差
 * 给你一个 m x n 的整数矩阵 grid 和一个整数 k。
 * 对于矩阵 grid 中的每个连续的 k x k 子矩阵，计算其中任意两个 不同值 之间的 最小绝对差 。
 * 返回一个大小为 (m - k + 1) x (n - k + 1) 的二维数组 ans，其中 ans[i][j] 表示以 grid 中坐标 (i, j) 为左上角的子矩阵的最小绝对差。
 * 注意：如果子矩阵中的所有元素都相同，则答案为 0。
 * 子矩阵 (x1, y1, x2, y2) 是一个由选择矩阵中所有满足 x1 <= x <= x2 且 y1 <= y <= y2 的单元格 matrix[x][y] 组成的矩阵。
 * 1 <= m == grid.length <= 30
 * 1 <= n == grid[i].length <= 30
 * -10^5 <= grid[i][j] <= 10^5
 * 1 <= k <= min(m, n)
 */
public class Solution {

    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m - k + 1][n - k + 1];
        int[] arr = new int[k * k];
        for (int i = 0; i < m - k + 1; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                int index = 0;
                for (int x = 0; x < k; x++) {
                    for (int y = 0; y < k; y++) {
                        arr[index++] = grid[i + x][j + y];
                    }
                }
                Arrays.sort(arr);
                int ans = Integer.MAX_VALUE;
                for (int z = 1; z < arr.length; z++) {
                    if (arr[z] > arr[z - 1]) {
                        ans = Math.min(ans, arr[z] - arr[z - 1]);
                    }
                }
                if (ans != Integer.MAX_VALUE) {
                    res[i][j] = ans;
                }
            }
        }
        return res;
    }

}
