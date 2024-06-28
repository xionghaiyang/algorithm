package com.sean.leetcode;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/27 14:21
 */
public class LeetCode79 {

    /**
     * https://leetcode.cn/problems/word-search/
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
     * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     */

    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    int m;
    int n;
    boolean[][] visit;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j) {
        if (board[i][j] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }
        visit[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int ni = i + dirs[k][0];
            int nj = j + dirs[k][1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visit[ni][nj]) {
                if (dfs(board, word, index + 1, ni, nj)) {
                    return true;
                }
            }
        }
        visit[i][j] = false;
        return false;
    }

}
