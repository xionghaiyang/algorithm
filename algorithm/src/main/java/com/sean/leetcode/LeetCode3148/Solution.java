package com.sean.leetcode.LeetCode3148;

import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-08-15 10:42
 * @Description https://leetcode.cn/problems/maximum-difference-score-in-a-grid/
 * 3148. 矩阵中的最大得分
 * 给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。
 * 你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。
 * 从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。
 * 你可以从 任一 单元格开始，并且必须至少移动一次。
 * 返回你能得到的 最大 总得分。
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 */
public class Solution {

    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[] preCol = new int[n];
        Arrays.fill(preCol, Integer.MIN_VALUE);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            int preRow = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                int f = Integer.MIN_VALUE;
                if (i > 0) {
                    f = Math.max(f, grid.get(i).get(j) + preCol[j]);
                }
                if (j > 0) {
                    f = Math.max(f, grid.get(i).get(j) + preRow);
                }
                res = Math.max(res, f);
                preCol[j] = Math.max(preCol[j], Math.max(f, 0) - grid.get(i).get(j));
                preRow = Math.max(preRow, Math.max(f, 0) - grid.get(i).get(j));
            }
        }
        return res;
    }

    public int maxScore1(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] preMin = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(preMin[i], Integer.MAX_VALUE);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pre = Integer.MAX_VALUE;
                if (i > 0) {
                    Math.min(pre, preMin[i - 1][j]);
                }
                if (j > 0) {
                    pre = Math.min(pre, preMin[i][j - 1]);
                }
                //i=j=0时没有转移
                if (i + j > 0) {
                    res = Math.max(res, grid.get(i).get(j) - pre);
                }
                preMin[i][j] = Math.min(pre, grid.get(i).get(j));
            }
        }
        return res;
    }

    public int maxScore2(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] preMin = new int[2][n];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(preMin[i], Integer.MAX_VALUE);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            Arrays.fill(preMin[i & 1], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                int pre = Integer.MAX_VALUE;
                if (i > 0) {
                    pre = Math.min(pre, preMin[(i - 1) & 1][j]);
                }
                if (j > 0) {
                    pre = Math.min(pre, preMin[i & 1][j - 1]);
                }
                //i=j=0时没有转移
                if (i + j > 0) {
                    res = Math.max(res, grid.get(i).get(j) - pre);
                }
                preMin[i & 1][j] = Math.min(pre, grid.get(i).get(j));
            }
        }
        return res;
    }

}
