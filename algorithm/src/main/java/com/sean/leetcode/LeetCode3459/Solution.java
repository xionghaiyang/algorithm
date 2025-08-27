package com.sean.leetcode.LeetCode3459;

/**
 * @Author xionghaiyang
 * @Date 2025-08-27 07:04
 * @Description https://leetcode.cn/problems/length-of-longest-v-shaped-diagonal-segment
 * 3459. 最长 V 形对角线段的长度
 * 给你一个大小为 n x m 的二维整数矩阵 grid，其中每个元素的值为 0、1 或 2。
 * V 形对角线段 定义如下：
 * 线段从 1 开始。
 * 后续元素按照以下无限序列的模式排列：2, 0, 2, 0, ...。
 * 该线段：
 * 起始于某个对角方向（左上到右下、右下到左上、右上到左下或左下到右上）。
 * 沿着相同的对角方向继续，保持 序列模式 。
 * 在保持 序列模式 的前提下，最多允许 一次顺时针 90 度转向 另一个对角方向。
 * 返回最长的 V 形对角线段 的 长度 。
 * 如果不存在有效的线段，则返回 0。
 * n == grid.length
 * m == grid[i].length
 * 1 <= n, m <= 500
 * grid[i][j] 的值为 0、1 或 2。
 */
public class Solution {

    private int[][] dirs = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[m][n][4];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                //理论最大值
                int[] maxs = {m - i, j + 1, i + 1, n - j};
                for (int k = 0; k < 4; k++) {
                    if (maxs[k] > res) {
                        res = Math.max(res, dfs(i, j, k, true, 2, grid, memo) + 1);
                    }
                }
            }
        }
        return res;
    }

    //上一步在(i,j)
    //移动方向为dirs[k]
    //是否可以右转canTurn
    //当前位置目标值target
    private int dfs(int i, int j, int k, boolean canTurn, int target, int[][] grid, int[][][] memo) {
        i += dirs[k][0];
        j += dirs[k][1];
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != target) {
            return 0;
        }
        if (!canTurn && memo[i][j][k] > 0) {
            return memo[i][j][k];
        }
        //直行
        int res = dfs(i, j, k, canTurn, 2 - target, grid, memo) + 1;
        if (!canTurn) {
            return memo[i][j][k] = res;
        }
        //理论最大值
        int[] maxs = {grid.length - i, j + 1, i + 1, grid[i].length - j};
        k = (k + 1) % 4;
        if (Math.min(maxs[k], maxs[(k + 3) % 4]) > res) {
            //右转
            res = Math.max(res, dfs(i, j, k, false, 2 - target, grid, memo) + 1);
        }
        return res;
    }

}
