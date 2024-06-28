package com.sean.leetcode.LeetCode2944;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-10 11:41
 * @Description: https://leetcode.cn/problems/minimum-number-of-coins-for-fruits/
 * 2944. 购买水果需要的最少金币数
 * 你在一个水果超市里，货架上摆满了玲琅满目的奇珍异果。
 * 给你一个下标从 1 开始的数组 prices ，其中 prices[i] 表示你购买第 i 个水果需要花费的金币数目。
 * 水果超市有如下促销活动：
 * 如果你花费 price[i] 购买了水果 i ，那么接下来的 i 个水果你都可以免费获得。
 * 注意 ，即使你 可以 免费获得水果 j ，你仍然可以花费 prices[j] 个金币去购买它以便能免费获得接下来的 j 个水果。
 * 请你返回获得所有水果所需要的 最少 金币数。
 */
public class Solution {

    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int[] memo = new int[(n + 1) / 2];
        return dfs(1, prices, memo);
    }

    private int dfs(int i, int[] prices, int[] memo) {
        if (i * 2 >= prices.length) {
            return prices[i - 1];
        }
        if (memo[i] != 0) {
            return memo[i];
        }
        int res = Integer.MAX_VALUE;
        for (int j = i + 1; j <= i * 2 + 1; j++) {
            res = Math.min(res, dfs(j, prices, memo));
        }
        memo[i] = res + prices[i - 1];
        return memo[i];
    }

    public int minimumCoins1(int[] prices) {
        int n = prices.length;
        for (int i = (n + 1) / 2 - 1; i > 0; i--) {
            int mn = Integer.MAX_VALUE;
            for (int j = i; j <= i * 2; j++) {
                mn = Math.min(mn, prices[j]);
            }
            prices[i - 1] += mn;
        }
        return prices[0];
    }

    public int minimumCoins2(int[] prices) {
        int n = prices.length;
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{n + 1, 0});
        for (int i = n; i > 0; i--) {
            while (q.peekLast()[0] > i * 2 + 1) {
                q.pollLast();
            }
            int f = prices[i - 1] + q.peekLast()[1];
            while (f <= q.peekFirst()[1]) {
                q.pollFirst();
            }
            q.addFirst(new int[]{i, f});
        }
        return q.peekFirst()[1];
    }

}
