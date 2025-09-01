package com.sean.leetcode.LeetCode2596;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-01 11:01
 * @Description https://leetcode.cn/problems/check-knight-tour-configuration
 * 2596. 检查骑士巡视方案
 * 骑士在一张 n x n 的棋盘上巡视。在 有效 的巡视方案中，骑士会从棋盘的 左上角 出发，并且访问棋盘上的每个格子 恰好一次 。
 * 给你一个 n x n 的整数矩阵 grid ，由范围 [0, n * n - 1] 内的不同整数组成，其中 grid[row][col] 表示单元格 (row, col) 是骑士访问的第 grid[row][col] 个单元格。
 * 骑士的行动是从下标 0 开始的。
 * 如果 grid 表示了骑士的有效巡视方案，返回 true；否则返回 false。
 * 注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。
 * 下图展示了骑士从某个格子出发可能的八种行动路线。
 * n == grid.length == grid[i].length
 * 3 <= n <= 7
 * 0 <= grid[row][col] < n * n
 * grid 中的所有整数 互不相同
 */
public class Solution {

    public boolean checkValidGrid(int[][] grid) {
        int[][] dirs = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        if (grid[0][0] == 0) {
            queue.offer(new int[]{0, 0});
        } else {
            return false;
        }
        int cur = 0;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            cur++;
            for (int[] dir : dirs) {
                int x = cell[0] + dir[0], y = cell[1] + dir[1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == cur) {
                    queue.offer(new int[]{x, y});
                    break;
                }
            }
        }
        return cur == n * n;
    }

}
