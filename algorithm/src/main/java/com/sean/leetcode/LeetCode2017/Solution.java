package com.sean.leetcode.LeetCode2017;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-19 18:07
 * @Description: https://leetcode.cn/problems/grid-game/description/
 * 2017. 网格游戏
 * 给你一个下标从 0 开始的二维数组 grid ，数组大小为 2 x n ，其中 grid[r][c] 表示矩阵中 (r, c) 位置上的点数。
 * 现在有两个机器人正在矩阵上参与一场游戏。
 * 两个机器人初始位置都是 (0, 0) ，目标位置是 (1, n-1) 。
 * 每个机器人只会 向右 ((r, c) 到 (r, c + 1)) 或 向下 ((r, c) 到 (r + 1, c)) 。
 * 游戏开始，第一个 机器人从 (0, 0) 移动到 (1, n-1) ，并收集路径上单元格的全部点数。
 * 对于路径上所有单元格 (r, c) ，途经后 grid[r][c] 会重置为 0 。
 * 然后，第二个 机器人从 (0, 0) 移动到 (1, n-1) ，同样收集路径上单元的全部点数。注意，它们的路径可能会存在相交的部分。
 * 第一个 机器人想要打击竞争对手，使 第二个 机器人收集到的点数 最小化 。
 * 与此相对，第二个 机器人想要 最大化 自己收集到的点数。
 * 两个机器人都发挥出自己的 最佳水平 的前提下，返回 第二个 机器人收集到的 点数 。
 */
public class Solution {

    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long sum1 = 0, sum2 = 0;
        for (int i = 1; i < n; i++) {
            sum1 += grid[0][i];
        }
        long res = sum1;
        for (int i = 1; i < n; i++) {
            sum1 -= grid[0][i];
            sum2 += grid[1][i - 1];
            res = Math.min(res, Math.max(sum1, sum2));
        }
        return res;
    }

}
