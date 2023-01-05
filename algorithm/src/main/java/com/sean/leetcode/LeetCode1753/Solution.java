package com.sean.leetcode.LeetCode1753;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-26 15:18
 * @Description: https://leetcode.cn/problems/maximum-score-from-removing-stones/
 * 1753. 移除石子的最大得分
 * 你正在玩一个单人游戏，面前放置着大小分别为 a​​​​​​、b 和 c​​​​​​ 的 三堆 石子。
 * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。
 * 当存在 两个或更多 的空堆时，游戏停止。
 * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
 */
public class Solution {

    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c;
        int maxVal = Math.max(Math.max(a, b), c);
        return Math.min(sum - maxVal, sum / 2);
    }

}
