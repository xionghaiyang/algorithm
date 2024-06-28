package com.sean.leetcode.LeetCode1263;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-08 08:09
 * @Description: https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location/
 * 1263. 推箱子
 * 「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 * 游戏地图用大小为 m x n 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
 * 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
 * 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符 '.' 表示，意味着可以自由行走。
 * 墙用字符 '#' 表示，意味着障碍物，不能通行。 
 * 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。
 */
public class Solution {

    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = -1, sy = -1;//玩家初始位置
        int bx = -1, by = -1;//箱子初始位置
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == 'S') {
                    sx = x;
                    sy = y;
                } else if (grid[x][y] == 'B') {
                    bx = x;
                    by = y;
                }
            }
        }
        int s = sx * n + sy;
        int b = bx * n + by;
        int[][] dp = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //初始状态的推动次数为0
        dp[s][b] = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{s, b});
        while (!queue.isEmpty()) {
            Queue<int[]> tmp = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0];
                int sx1 = s1 / n, sy1 = s1 % n;
                int b1 = arr[1];
                int bx1 = b1 / n, by1 = b1 % n;
                if (grid[bx1][by1] == 'T') {//箱子已被推到目标处
                    return dp[s1][b1];
                }
                for (int[] dir : dirs) {//玩家向四个方向移动到另一个状态
                    int sx2 = sx1 + dir[0];
                    int sy2 = sy1 + dir[1];
                    int s2 = sx2 * n + sy2;
                    if (!ok(grid, m, n, sx2, sy2)) {//玩家位置不合法
                        continue;
                    }
                    if (bx1 == sx2 && by1 == sy2) {//推动箱子
                        int bx2 = bx1 + dir[0];
                        int by2 = by1 + dir[1];
                        int b2 = bx2 * n + by2;
                        if (!ok(grid, m, n, bx2, by2) || dp[s2][b2] <= dp[s1][b1] + 1) {//箱子位置不合法或状态已访问
                            continue;
                        }
                        dp[s2][b2] = dp[s1][b1] + 1;
                        tmp.offer(new int[]{s2, b2});
                    } else {//玩家移动到s2，箱子不动
                        if (dp[s2][b1] <= dp[s1][b1]) {//状态已访问
                            continue;
                        }
                        dp[s2][b1] = dp[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }
            queue = tmp;
        }
        return -1;
    }

    //不越界且不在墙上
    private boolean ok(char[][] grid, int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';
    }

}
