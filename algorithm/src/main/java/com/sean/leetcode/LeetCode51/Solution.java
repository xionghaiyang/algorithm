package com.sean.leetcode.LeetCode51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-29 19:26
 * @Description: https://leetcode.cn/problems/n-queens/
 * 51. N 皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class Solution {

    private int n;
    private char[][] chess;
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chess[i], '.');
        }
        process(0);
        return res;
    }

    private void process(int row) {
        if (row == n) {
            res.add(build());
            return;
        }
        for (int col = 0; col < n; col++) {
            if (valid(row, col)) {
                chess[row][col] = 'Q';
                process(row + 1);
                chess[row][col] = '.';
            }
        }
    }

    private boolean valid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> build() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(String.valueOf(chess[i]));
        }
        return res;
    }

}
