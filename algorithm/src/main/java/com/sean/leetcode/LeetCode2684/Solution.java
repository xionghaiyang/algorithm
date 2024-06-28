package com.sean.leetcode.LeetCode2684;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-16 18:41
 * @Description: https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid/
 * 2684. 矩阵中移动的最大次数
 * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
 * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
 * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
 * 返回你在矩阵中能够 移动 的 最大 次数。
 */
public class Solution {

    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            grid[i][0] *= -1; //入队标记
        }
        for (int j = 0; j < n - 1; j++) {
            boolean ok = false;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] > 0) {//不在队列
                    continue;
                }
                for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
                    if (grid[k][j + 1] > -grid[i][j]) {
                        grid[k][j + 1] *= -1;//入队标记
                        ok = true;
                    }
                }
            }
            if (!ok) {//无法再往右走了
                return j;
            }
        }
        return n - 1;
    }

    private int[][] grid;
    private int res;
    private int m;
    private int n;

    public int maxMoves1(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            dfs(i, 0); // 从第一列的任一单元格出发
        }
        return res;
    }

    private void dfs(int i, int j) {
        res = Math.max(res, j);
        if (res == n - 1) {
            return;
        }
        for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
            if (grid[k][j + 1] > grid[i][j]) {
                dfs(k, j + 1);
            }
        }
        grid[i][j] = 0;
    }

    public int maxMoves2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] vis = new int[m];
        Arrays.fill(vis, -1);
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            q.add(i);
        }
        for (int j = 0; j < n - 1; j++) {
            List<Integer> next = new ArrayList<>();
            for (int i : q) {
                for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
                    if (vis[k] != j && grid[k][j + 1] > grid[i][j]) {
                        vis[k] = j;//第k行目前最右访问到了j
                        next.add(k);
                    }
                }
            }
            if (next.isEmpty()) {
                return j;
            }
            q = next;
        }
        return n - 1;
    }

}
