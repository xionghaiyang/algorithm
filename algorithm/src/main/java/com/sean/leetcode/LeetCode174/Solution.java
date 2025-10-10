package com.sean.leetcode.LeetCode174;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-10 12:29
 * @Description https://leetcode.cn/problems/dungeon-game
 * 174. 地下城游戏
 * 恶魔们抓住了公主并将她关在了地下城 dungeon 的 右下角 。
 * 地下城是由 m x n 个房间组成的二维网格。
 * 我们英勇的骑士最初被安置在 左上角 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。
 * 如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快解救公主，骑士决定每次只 向右 或 向下 移动一步。
 * 返回确保骑士能够拯救到公主所需的最低初始健康点数。
 * 注意：任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 */
public class Solution {

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return 1 + dfs(dungeon, memo, 0, 0);
    }

    private int dfs(int[][] dungeon, int[][] memo, int x, int y) {
        if (x >= dungeon.length || y >= dungeon[0].length) {
            return Integer.MAX_VALUE;
        }
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        if (x == dungeon.length - 1 && y == dungeon[0].length - 1) {
            if (dungeon[x][y] > 0) {
                return memo[x][y] = 0;
            } else {
                return memo[x][y] = -dungeon[x][y];
            }
        }
        int right = dfs(dungeon, memo, x + 1, y);
        int down = dfs(dungeon, memo, x, y + 1);
        int need = Math.min(right, down) - dungeon[x][y];
        return memo[x][y] = need < 0 ? 0 : need;
    }

}
