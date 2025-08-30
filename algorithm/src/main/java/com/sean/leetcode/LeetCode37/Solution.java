package com.sean.leetcode.LeetCode37;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 13:23
 * @Description: https://leetcode.cn/problems/sudoku-solver
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class Solution {

    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][] box = new boolean[9][9];
    private List<int[]> spaces = new ArrayList<>();
    private boolean valid = false;

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '1';
                    int boxIndex = (i / 3) * 3 + j / 3;
                    row[i][digit] = true;
                    col[j][digit] = true;
                    box[boxIndex][digit] = true;
                }
            }
        }
        dfs(board, 0);
    }

    private void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int i = space[0], j = space[1], boxIndex = (i / 3) * 3 + j / 3;
        for (int digit = 0; digit < 9 && !valid; digit++) {
            if (!row[i][digit] && !col[j][digit] && !box[boxIndex][digit]) {
                row[i][digit] = true;
                col[j][digit] = true;
                box[boxIndex][digit] = true;
                board[i][j] = (char) (digit + '1');
                dfs(board, pos + 1);
                row[i][digit] = false;
                col[j][digit] = false;
                box[boxIndex][digit] = false;
            }
        }
    }

}
