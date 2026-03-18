package com.sean.leetcode.LeetCodeInterview0812;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-18 18:52
 * @Description https://leetcode.cn/problems/eight-queens-lcci
 * 面试题 08.12. 八皇后
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
 * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * 注意：本题相对原题做了扩展
 */
public class Solution {

    private int n;
    private char[][] strs;
    private List<List<String>> res;
    private boolean[] anti;
    private boolean[] col;
    private boolean[] diag;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        strs = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(strs[i], '.');
        }
        anti = new boolean[2 * n - 1];
        col = new boolean[n];
        diag = new boolean[2 * n - 1];
        res = new ArrayList<>();
        process(0);
        return res;
    }

    private void process(int i) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (char[] str : strs) {
                list.add(String.valueOf(str));
            }
            res.add(list);
            return;
        }
        for (int j = 0; j < n; j++) {
            int antiIndex = i + j, diagIndex = i - j + n - 1;
            if (anti[antiIndex] || col[j] || diag[diagIndex]) {
                continue;
            }
            anti[antiIndex] = col[j] = diag[diagIndex] = true;
            strs[i][j] = 'Q';
            process(i + 1);
            strs[i][j] = '.';
            anti[antiIndex] = col[j] = diag[diagIndex] = false;
        }
    }

}
