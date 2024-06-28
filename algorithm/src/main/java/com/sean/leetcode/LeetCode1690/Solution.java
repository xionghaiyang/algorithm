package com.sean.leetcode.LeetCode1690;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-03 16:10
 * @Description: https://leetcode.cn/problems/stone-game-vii/
 * 1690. 石子游戏 VII
 * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。
 * 有 n 块石子排成一排。
 * 每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。
 * 当没有石头可移除时，得分较高者获胜。
 * 鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。
 * 爱丽丝的目标是最大限度地 扩大得分的差值 。
 * 给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。
 */
public class Solution {

    private int[] preSum;
    private int[][] dp;

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        dp = new int[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int res = Math.max(preSum[j + 1] - preSum[i + 1] - dfs(i + 1, j), preSum[j] - preSum[i] - dfs(i, j - 1));
        return dp[i][j] = res;
    }

}
