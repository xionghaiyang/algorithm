package com.sean.leetcode.LeetCodeInterview1604;

/**
 * @Author xionghaiyang
 * @Date 2026-03-22 18:40
 * @Description https://leetcode.cn/problems/tic-tac-toe-lcci
 * 面试题 16.04. 井字游戏
 * 设计一个算法，判断玩家是否赢了井字游戏。
 * 输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * 以下是井字游戏的规则：
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 */
public class Solution {

    public String tictactoe(String[] board) {
        int n = board.length;
        int[][] row = new int[n][2];
        int[][] col = new int[n][2];
        int[] diag = new int[2];
        int[] anti = new int[2];
        boolean hasEmpty = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                if (c == 'X') {
                    row[i][0]++;
                    col[j][0]++;
                    if (i == j) {
                        diag[0]++;
                    }
                    if (i + j == n - 1) {
                        anti[0]++;
                    }
                    if ((i == n - 1 || j == n - 1) && (row[i][0] == n || col[j][0] == n || diag[0] == n || anti[0] == n)) {
                        return "X";
                    }
                } else if (c == 'O') {
                    row[i][1]++;
                    col[j][1]++;
                    if (i == j) {
                        diag[1]++;
                    }
                    if (i + j == n - 1) {
                        anti[1]++;
                    }
                    if ((i == n - 1 || j == n - 1) && (row[i][1] == n || col[j][1] == n || diag[1] == n || anti[1] == n)) {
                        return "O";
                    }
                } else {
                    hasEmpty = true;
                }
            }
        }
        return hasEmpty ? "Pending" : "Draw";
    }

    public static void main(String[] args) {
        String[] board = {"O X", " XO", "X O"};
        Solution solution = new Solution();
        System.out.println(solution.tictactoe(board));
    }

}
