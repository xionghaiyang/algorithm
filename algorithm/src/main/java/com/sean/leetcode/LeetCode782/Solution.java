package com.sean.leetcode.LeetCode782;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-23 11:34
 * @Description: https://leetcode.cn/problems/transform-to-chessboard/
 * 782. 变为棋盘
 * 一个 n x n 的二维网络 board 仅由 0 和 1 组成 。每次移动，你能任意交换两列或是两行的位置。
 * 返回 将这个矩阵变为  “棋盘”  所需的最小移动次数 。如果不存在可行的变换，输出 -1。
 * “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
 */
public class Solution {

    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowMask = 0, colMask = 0;
        for (int i = 0; i < n; i++) {
            rowMask |= (board[0][i] << i);
            colMask |= (board[i][0] << i);
        }
        int reverseRowMask = ((1 << n) - 1) ^ rowMask;
        int reverseColMask = ((1 << n) - 1) ^ colMask;
        int rowCnt = 0, colCnt = 0;
        for (int i = 0; i < n; i++) {
            int currRowMask = 0;
            int currColMask = 0;
            for (int j = 0; j < n; j++) {
                currRowMask |= (board[i][j] << j);
                currColMask |= (board[j][i] << j);
            }
            //检测每一行的状态是否合法
            if (currRowMask != rowMask && currRowMask != reverseRowMask) {
                return -1;
            } else if (currRowMask == rowMask) {
                rowCnt++;
            }
            //检测每一列的状态是否合法
            if (currColMask != colMask && currColMask != reverseColMask) {
                return -1;
            } else if (currColMask == colMask) {
                colCnt++;
            }
        }
        int rowMoves = getMoves(rowMask, rowCnt, n);
        int colMoves = getMoves(colMask, colCnt, n);
        return (rowMoves == -1 || rowMoves == -1) ? -1 : (rowMoves + colMoves);
    }

    private int getMoves(int mask, int count, int n) {
        int ones = Integer.bitCount(mask);
        if ((n & 1) == 1) {
            //如果n为奇数，则每一行中1与0的数目相差为1，且满足相邻行交替
            if (Math.abs(n - 2 * ones) != 1 || Math.abs(n - 2 * count) != 1) {
                return -1;
            }
            if (ones == (n >> 1)) {
                //以0为开头的最小交换次数
                return (n >> 1) - Integer.bitCount(mask & 0xAAAAAAAA);
            } else {
                //以1为开头的最小交换次数
                return ((n + 1) >> 1) - Integer.bitCount(mask & 0x55555555);
            }
        } else {
            //如果n为偶数，则每一行中1与0的数目相等，且满足相邻行交替
            if (ones != (n >> 1) || count != (n >> 1)) {
                return -1;
            }
            //找到行的最小交换次数
            return Math.min((n >> 1) - Integer.bitCount(mask & 0xAAAAAAAA), (n >> 1) - Integer.bitCount(mask & 0x55555555));
        }
    }

}
