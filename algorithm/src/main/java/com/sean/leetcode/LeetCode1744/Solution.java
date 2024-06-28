package com.sean.leetcode.LeetCode1744;

/**
 * @Author xionghaiyang
 * @Date 2024-05-07 18:09
 * @Description https://leetcode.cn/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/
 * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
 * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。
 * 同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
 * 你按照如下规则进行一场游戏：
 * 你从第 0 天开始吃糖果。
 * 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
 * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
 * 请你构建一个布尔型数组 answer ，用以给出 queries 中每一项的对应答案。此数组满足：
 * answer.length == queries.length 。answer[i] 是 queries[i] 的答案。
 * answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；
 * 否则 answer[i] 为 false 。
 * 注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * 请你返回得到的数组 answer 。
 */
public class Solution {

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        long[] preSum = new long[n];
        preSum[0] = candiesCount[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + candiesCount[i];
        }
        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int favoriteType = query[0], favoriteDay = query[1], dailyCap = query[2];
            long x1 = favoriteDay + 1;
            long y1 = x1 * dailyCap;
            long x2 = favoriteType == 0 ? 1 : preSum[favoriteType - 1] + 1;
            long y2 = preSum[favoriteType];
            res[i] = !(x1 > y2 || y1 < x2);
        }
        return res;
    }

}
