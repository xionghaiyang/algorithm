package com.sean.leetcode.LeetCode1833;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-21 06:15
 * @Description: https://leetcode.cn/problems/maximum-ice-cream-bars
 * 1833. 雪糕的最大数量
 * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
 * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。
 * Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
 * 注意：Tony 可以按任意顺序购买雪糕。
 * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
 * 你必须使用计数排序解决此问题。
 * costs.length == n
 * 1 <= n <= 10^5
 * 1 <= costs[i] <= 10^5
 * 1 <= coins <= 10^8
 */
public class Solution {

    public int maxIceCream(int[] costs, int coins) {
        int[] freq = new int[100_001];
        for (int cost : costs) {
            freq[cost]++;
        }
        int res = 0;
        for (int i = 1; i <= 100_000; i++) {
            if (coins >= i) {
                int cur = Math.min(freq[i], coins / i);
                res += cur;
                coins -= i * cur;
            } else {
                break;
            }
        }
        return res;
    }

}
