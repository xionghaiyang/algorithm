package com.sean.leetcode.LeetCode79;

/**
 * @Author xionghaiyang
 * @Date 2025-06-13 06:57
 * @Description https://leetcode.cn/problems/word-search
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 */
public class Solution {

    private int m;
    private int n;
    private char[][] board;
    private char[] str;
    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        str = word.toCharArray();
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (process(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean process(int i, int j, int k) {
        if (board[i][j] != str[k]) {
            return false;
        } else if (k == str.length - 1) {
            return true;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && process(x, y, k + 1)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

}
