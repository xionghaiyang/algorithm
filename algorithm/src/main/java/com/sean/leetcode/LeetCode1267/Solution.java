package com.sean.leetcode.LeetCode1267;

/**
 * @Author xionghaiyang
 * @Date 2025-09-15 17:28
 * @Description https://leetcode.cn/problems/count-servers-that-communicate
 * 1267. 统计参与通信的服务器
 * 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
 * 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
 * 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 */
public class Solution {

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (rows[i] >= 2 || cols[j] >= 2) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

}
