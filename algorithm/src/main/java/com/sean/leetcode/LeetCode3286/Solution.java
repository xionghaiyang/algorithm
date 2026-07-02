package com.sean.leetcode.LeetCode3286;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-02 20:38
 * @Description: https://leetcode.cn/problems/find-a-safe-walk-through-a-grid
 * 3286. 穿越网格图的安全路径
 * 给你一个 m x n 的二进制矩形 grid 和一个整数 health 表示你的健康值。
 * 你开始于矩形的左上角 (0, 0) ，你的目标是矩形的右下角 (m - 1, n - 1) 。
 * 你可以在矩形中往上下左右相邻格子移动，但前提是你的健康值始终是 正数 。
 * 对于格子 (i, j) ，如果 grid[i][j] = 1 ，那么这个格子视为 不安全 的，会使你的健康值减少 1 。
 * 如果你可以到达最终的格子，请你返回 true ，否则返回 false 。
 * 注意 ，当你在最终格子的时候，你的健康值也必须为 正数 。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 2 <= m * n
 * 1 <= health <= m + n
 * grid[i][j] 要么是 0 ，要么是 1 。
 */
public class Solution {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offerFirst(new int[]{0, 0});
        dis[0][0] = grid.get(0).get(0);
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int x = cur[0], y = cur[1];
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            for (int[] dir : DIRS) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                int cost = dis[x][y] + grid.get(nx).get(ny);
                if (cost >= health) {
                    continue;
                }
                if (cost < dis[nx][ny]) {
                    dis[nx][ny] = cost;
                    if (grid.get(nx).get(ny) == 0) {
                        q.offerFirst(new int[]{nx, ny});
                    } else {
                        q.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }
        return false;
    }

}
