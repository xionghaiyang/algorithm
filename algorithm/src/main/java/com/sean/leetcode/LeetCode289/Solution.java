package com.sean.leetcode.LeetCode289;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 11:44
 * @Description: https://leetcode.cn/problems/game-of-life/?envType=study-plan-v2&envId=top-interview-150
 * 289. 生命游戏
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
 * 每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 */
public class Solution {

    private int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && Math.abs(board[x][y]) == 1) {
                        liveNeighbors++;
                    }
                }
                //规则1或者规则3
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    //-1代表这个细胞过去是获得现在死了
                    board[i][j] = -1;
                }
                //规则4
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    //2代表这个细胞过去是死了现在活了
                    board[i][j] = 2;
                }
            }
        }
        //遍历board得到一次更新后的状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] > 0 ? 1 : 0);
            }
        }
    }

}
