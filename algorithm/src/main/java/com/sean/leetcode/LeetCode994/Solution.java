package com.sean.leetcode.LeetCode994;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-01 19:42
 * @Description https://leetcode.cn/problems/rotting-oranges
 * 994. 腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 */
public class Solution {

    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int res = 0;
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        while (fresh > 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] arr = queue.poll();
                for (int[] dir : dirs) {
                    int i = arr[0] + dir[0];
                    int j = arr[1] + dir[1];
                    if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
                        grid[i][j] = 2;
                        queue.offer(new int[]{i, j});
                        fresh--;
                    }
                }
            }
            res++;
        }
        return fresh > 0 ? -1 : res;
    }

}
