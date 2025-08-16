package com.sean.leetcode.LeetCode837;

/**
 * @Author xionghaiyang
 * @Date 2025-08-17 06:27
 * @Description https://leetcode.cn/problems/new-21-game
 * 837. 新 21 点
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * 爱丽丝以 0 分开始，并在她的得分少于 k 分时抽取数字。
 * 抽取时，她从 [1, maxPts] 的范围中随机获得一个整数作为分数进行累计，其中 maxPts 是一个整数。
 * 每次抽取都是独立的，其结果具有相同的概率。
 * 当爱丽丝获得 k 分 或更多分 时，她就停止抽取数字。
 * 爱丽丝的分数不超过 n 的概率是多少？
 * 与实际答案误差不超过 10^-5 的答案将被视为正确答案。
 * 0 <= k <= n <= 10^4
 * 1 <= maxPts <= 10^4
 */
public class Solution {

    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        //dp[i]表示从得分i的情况开始游戏并获胜的概率
        double[] dp = new double[k + maxPts];
        for (int i = k; i <= n && i < k + maxPts; i++) {
            dp[i] = 1.0;
        }
        dp[k - 1] = 1.0 * Math.min(n - k + 1, maxPts) / maxPts;
        for (int i = k - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + maxPts + 1] - dp[i + 1]) / maxPts;
        }
        return dp[0];
    }

}
