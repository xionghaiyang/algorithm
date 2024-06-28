package com.sean.leetcode.LeetCode934;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-17 08:03
 * @Description: https://leetcode.cn/problems/shortest-bridge/?plan=graph&plan_progress=zq56763
 * 934. 最短的桥
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 * 返回必须翻转的 0 的最小数目。
 */
public class Solution {

    public int shortestBridge(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length;
        int n = grid[0].length;
        //使用1和2标记两座岛
        int[][] colors = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (colors[i][j] == 0 && grid[i][j] == 1) {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i * m + j);
                    colors[i][j] = ++index;
                    while (!queue.isEmpty()) {
                        int num = queue.poll();
                        int x = num / m;
                        int y = num % m;
                        for (int[] dir : dirs) {
                            int nx = x + dir[0];
                            int ny = y + dir[1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1 && colors[nx][ny] == 0) {
                                colors[nx][ny] = index;
                                queue.offer(nx * m + ny);
                            }
                        }
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (colors[i][j] == 1) {//第一座岛元素加入岛队列
                    queue.add(new int[]{i, j, 0});
                } else if (colors[i][j] == 2) {//第二座岛元素加入岛哈希表
                    set.add(i * m + j);
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            int depth = cell[2];
            if (set.contains(x * m + y)) {//已经接触到第二座岛
                return depth - 1;
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && colors[nx][ny] != 1) {
                    queue.add(new int[]{nx, ny, depth + 1});
                    colors[nx][ny] = 1;
                }
            }
        }
        return -1;
    }

}
