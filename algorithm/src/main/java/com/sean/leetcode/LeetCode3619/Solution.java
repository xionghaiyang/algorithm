package com.sean.leetcode.LeetCode3619;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 09:57
 * @Description https://leetcode.cn/problems/count-islands-with-total-value-divisible-by-k
 * 3619. 总价值可以被 K 整除的岛屿数目
 * 给你一个 m x n 的矩阵 grid 和一个正整数 k。
 * 一个 岛屿 是由 正 整数（表示陆地）组成的，并且陆地间 四周 连通（水平或垂直）。
 * 一个岛屿的总价值是该岛屿中所有单元格的值之和。
 * 返回总价值可以被 k 整除 的岛屿数量。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * 0 <= grid[i][j] <= 10^6
 * 1 <= k < = 10^6
 */
public class Solution {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && process(grid, i, j, k) % k == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    private int process(int[][] grid, int i, int j, int k) {
        int res = grid[i][j];
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length && grid[x][y] > 0) {
                res = (res + process(grid, x, y, k)) % k;
            }
        }
        return res;
    }

    public int countIslands1(int[][] grid, int k) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        int res = 0, temp = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    queue.offer(new int[]{i, j});
                    temp = grid[i][j];
                    grid[i][j] = 0;
                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();
                        int x = arr[0], y = arr[1];
                        if (x - 1 >= 0 && grid[x - 1][y] > 0) {
                            queue.offer(new int[]{x - 1, y});
                            temp = (temp + grid[x - 1][y]) % k;
                            grid[x - 1][y] = 0;
                        }
                        if (x + 1 < m && grid[x + 1][y] > 0) {
                            queue.offer(new int[]{x + 1, y});
                            temp = (temp + grid[x + 1][y]) % k;
                            grid[x + 1][y] = 0;
                        }
                        if (y - 1 >= 0 && grid[x][y - 1] > 0) {
                            queue.offer(new int[]{x, y - 1});
                            temp = (temp + grid[x][y - 1]) % k;
                            grid[x][y - 1] = 0;
                        }
                        if (y + 1 < n && grid[x][y + 1] > 0) {
                            queue.offer(new int[]{x, y + 1});
                            temp = (temp + grid[x][y + 1]) % k;
                            grid[x][y + 1] = 0;
                        }
                    }
                    if (temp % k == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

}
