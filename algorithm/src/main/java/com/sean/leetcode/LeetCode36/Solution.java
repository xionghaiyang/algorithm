package com.sean.leetcode.LeetCode36;

/**
 * @Author xionghaiyang
 * @Date 2025-07-08 09:42
 * @Description https://leetcode.cn/problems/valid-sudoku
 * 36. 有效的数独
 * 请你判断一个 9 x 9 的数独是否有效。
 * 只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字（1-9）或者 '.'
 */
public class Solution {

    public boolean isValidSudoku(char[][] board) {
        //row[i][j]表示第i行的数字j是否已经填好
        boolean[][] row = new boolean[9][9];
        //col[i][j]表示第i列的数字j是否已经填好
        boolean[][] col = new boolean[9][9];
        //box[i][j]表示第i个box的数字j是否已经填好
        boolean[][] box = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '1';
                int boxIndex = (i / 3) * 3 + j / 3;
                if (row[i][num] || col[j][num] || box[boxIndex][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                box[boxIndex][num] = true;
            }
        }
        return true;
    }

}
