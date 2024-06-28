package com.sean.leetcode.LeetCode994;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-01 19:42
 * @Description https://leetcode.cn/problems/rotting-oranges/?envType=study-plan-v2&envId=top-100-liked
 * 994. 腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class Solution {

    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int code = i * n + j;
                    queue.offer(code);
                    map.put(code, 0);
                }
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int code = queue.poll();
            int x = code / n, y = code % n;
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                    grid[nx][ny] = 2;
                    int ncode = nx * n + ny;
                    queue.offer(ncode);
                    map.put(ncode, map.get(code) + 1);
                    res = map.get(ncode);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return res;
    }

}
