package com.sean.leetcode.LeetCode2267;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-29 06:16
 * @Description: https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path
 * 2267. 检查是否有合法括号字符串路径
 * 一个括号字符串是一个 非空 且只包含 '(' 和 ')' 的字符串。
 * 如果下面 任意 条件为 真 ，那么这个括号字符串就是 合法的 。
 * 字符串是 () 。
 * 字符串可以表示为 AB（A 连接 B），A 和 B 都是合法括号序列。
 * 字符串可以表示为 (A) ，其中 A 是合法括号序列。
 * 给你一个 m x n 的括号网格图矩阵 grid 。
 * 网格图中一个 合法括号路径 是满足以下所有条件的一条路径：
 * 路径开始于左上角格子 (0, 0) 。
 * 路径结束于右下角格子 (m - 1, n - 1) 。
 * 路径每次只会向 下 或者向 右 移动。
 * 路径经过的格子组成的括号字符串是 合法 的。
 * 如果网格图中存在一条 合法括号路径 ，请返回 true ，否则返回 false 。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 要么是 '(' ，要么是 ')' 。
 */
public class Solution {

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (((m + n) & 1) == 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        boolean[][][] vis = new boolean[m][n][(m + n + 1) / 2];
        return dfs(grid, vis, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, boolean[][][] vis, int i, int j, int k) {
        int m = grid.length, n = grid[0].length;
        if (k > m - i + n - j - 1) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return k == 1;
        }
        if (vis[i][j][k]) {
            return false;
        }
        vis[i][j][k] = true;
        k += grid[i][j] == '(' ? 1 : -1;
        if (k < 0) {
            return false;
        }
        return (i < m - 1 && dfs(grid, vis, i + 1, j, k)) || (j < n - 1 && dfs(grid, vis, i, j + 1, k));
    }

}
