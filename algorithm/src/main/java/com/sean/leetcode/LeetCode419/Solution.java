package com.sean.leetcode.LeetCode419;

/**
 * @Author xionghaiyang
 * @Date 2024-06-11 08:18
 * @Description https://leetcode.cn/problems/battleships-in-a-board/
 * 419. 甲板上的战舰
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * 战舰 只能水平或者垂直放置在 board 上。
 * 换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。
 * 两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 */
public class Solution {

    private int m;
    private int n;
    private char[][] board;
    private boolean[][] visited;

    public int countBattleships(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && !visited[i][j]) {
                    process(i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void process(int i, int j) {
        visited[i][j] = true;
        if (j + 1 < n && board[i][j + 1] == 'X') {
            process(i, j + 1);
        }
        if (i + 1 < m && board[i + 1][j] == 'X') {
            process(i + 1, j);
        }
    }

    public int countBattleships1(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < n && board[i][k] == 'X'; k++) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < m && board[k][j] == 'X'; k++) {
                        board[k][j] = '.';
                    }
                    res++;
                }
            }
        }
        return res;
    }

    public int countBattleships2(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

}
