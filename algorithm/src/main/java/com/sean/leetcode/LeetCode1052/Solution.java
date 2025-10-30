package com.sean.leetcode.LeetCode1052;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-23 16:39
 * @Description: https://leetcode.cn/problems/grumpy-bookstore-owner
 * 1052. 爱生气的书店老板
 * 有一个书店老板，他的书店开了 n 分钟。
 * 每分钟都有一些顾客进入这家商店。
 * 给定一个长度为 n 的整数数组 customers ，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
 * 在某些时候，书店老板会生气。
 * 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
 * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
 */
public class Solution {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int[] s = new int[2];
        int maxS = 0;
        for (int i = 0; i < n; i++) {
            s[grumpy[i]] += customers[i];
            int left = i - minutes + 1;
            if (left < 0) {
                continue;
            }
            maxS = Math.max(maxS, s[1]);
            s[1] -= grumpy[left] > 0 ? customers[left] : 0;
        }
        //老板不生气时的顾客数量之和s0
        //长度为minutes的连续子数组中，老板生气时的顾客数量之和s1的最大值
        return s[0] + maxS;
    }

}
