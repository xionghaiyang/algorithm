package com.sean.leetcode.LeetCode1423;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-03 20:12
 * @Description: https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/
 * 1423. 可获得的最大点数
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 */
public class Solution {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        int res = sum;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[n - 1 - i] - cardPoints[k - 1 - i];
            res = Math.max(res, sum);
        }
        return res;
    }

}
