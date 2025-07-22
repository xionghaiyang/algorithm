package com.sean.leetcode.LeetCode52;

/**
 * @Author xionghaiyang
 * @Date 2025-07-22 15:42
 * @Description https://leetcode.cn/problems/n-queens-ii
 * 52. N 皇后 II
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * 1 <= n <= 9
 */
public class Solution {

    private int res = 0;

    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[n * 2 - 1];
        boolean[] diag2 = new boolean[n * 2 - 1];
        dfs(0, col, diag1, diag2);
        return res;
    }

    private void dfs(int r, boolean[] col, boolean[] diag1, boolean[] diag2) {
        int n = col.length;
        if (r == n) {
            res++;
            return;
        }
        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;
            if (!col[c] && !diag1[r + c] && !diag2[rc]) {
                col[c] = diag1[r + c] = diag2[rc] = true;
                dfs(r + 1, col, diag1, diag2);
                col[c] = diag1[r + c] = diag2[rc] = false;
            }
        }
    }

}
