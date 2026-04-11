package com.sean.leetcode.LeetCode999;

/**
 * @Author xionghaiyang
 * @Date 2026-04-11 18:08
 * @Description https://leetcode.cn/problems/available-captures-for-rook
 * 999. 可以被一步捕获的棋子数
 * 给定一个 8 x 8 的棋盘，只有一个 白色的车，用字符 'R' 表示。
 * 棋盘上还可能存在白色的象 'B' 以及黑色的卒 'p'。
 * 空方块用字符 '.' 表示。
 * 车可以按水平或竖直方向（上，下，左，右）移动任意个方格直到它遇到另一个棋子或棋盘的边界。
 * 如果它能够在一次移动中移动到棋子的方格，则能够 吃掉 棋子。
 * 注意：车不能穿过其它棋子，比如象和卒。
 * 这意味着如果有其它棋子挡住了路径，车就不能够吃掉棋子。
 * 返回白车 攻击 范围内 兵的数量。
 * board.length == 8
 * board[i].length == 8
 * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
 * 只有一个格子上存在 board[i][j] == 'R'
 */
public class Solution {

    public int numRookCaptures(char[][] board) {
        int m = board.length, n = board[0].length;
        int res = 0;
        out:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'R') {
                    for (int x = i - 1; x >= 0; x--) {
                        if (board[x][j] == 'B') {
                            break;
                        } else if (board[x][j] == 'p') {
                            res++;
                            break;
                        }
                    }
                    for (int x = i + 1; x < m; x++) {
                        if (board[x][j] == 'B') {
                            break;
                        } else if (board[x][j] == 'p') {
                            res++;
                            break;
                        }
                    }
                    for (int y = j - 1; y >= 0; y--) {
                        if (board[i][y] == 'B') {
                            break;
                        } else if (board[i][y] == 'p') {
                            res++;
                            break;
                        }
                    }
                    for (int y = j + 1; y < n; y++) {
                        if (board[i][y] == 'B') {
                            break;
                        } else if (board[i][y] == 'p') {
                            res++;
                            break;
                        }
                    }
                    break out;
                }
            }
        }
        return res;
    }

}
