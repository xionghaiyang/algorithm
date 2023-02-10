package com.sean.leetcode.LeetCode1815;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-28 13:35
 * @Description: https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts/
 * 1815. 得到新鲜甜甜圈的最多组数
 * 有一个甜甜圈商店，每批次都烤 batchSize 个甜甜圈。
 * 这个店铺有个规则，就是在烤一批新的甜甜圈时，之前 所有 甜甜圈都必须已经全部销售完毕。
 * 给你一个整数 batchSize 和一个整数数组 groups ，数组中的每个整数都代表一批前来购买甜甜圈的顾客，其中 groups[i] 表示这一批顾客的人数。
 * 每一位顾客都恰好只要一个甜甜圈。
 * 当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。
 * 如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心。
 * 你可以随意安排每批顾客到来的顺序。请你返回在此前提下，最多 有多少组人会感到开心。
 */
public class Solution {

    //内存溢出
    public int maxHappyGroups1(int batchSize, int[] groups) {
        if (groups == null || groups.length == 0) {
            return 0;
        }
        int n = groups.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        return process(batchSize, groups, 0, 0, dp);
    }

    private int process(int batchSize, int[] groups, int status, int rest, int[] dp) {
        if (dp[status] != -1) {
            return dp[status];
        }
        int n = groups.length;
        if (status == (1 << n) - 1) {
            dp[status] = rest == 0 ? 1 : 0;
            return dp[status];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if ((status & (1 << i)) == 0) {
                if (rest == 0) {
                    int cur = groups[i] % batchSize;
                    res = Math.max(res, 1 + process(batchSize, groups, status | (1 << i), cur == 0 ? 0 : batchSize - cur, dp));
                } else {
                    int cur = (groups[i] % batchSize - rest + batchSize) % batchSize;
                    res = Math.max(res, process(batchSize, groups, status | (1 << i), cur == 0 ? 0 : batchSize - cur, dp));
                }
            }
        }
        dp[status] = res;
        return dp[status];
    }

    private static final int K_WIDTH = 5;
    private static final int K_WIDTH_MASK = (1 << K_WIDTH) - 1;

    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] cnt = new int[batchSize];
        for (int x : groups) {
            cnt[x % batchSize]++;
        }
        long start = 0;
        for (int i = batchSize - 1; i >= 1; i--) {
            start = (start << K_WIDTH) | cnt[i];
        }
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(memo, batchSize, start) + cnt[0];
    }

    private int dfs(Map<Long, Integer> memo, int batchSize, long mask) {
        if (mask == 0) {
            return 0;
        }
        if (!memo.containsKey(mask)) {
            long total = 0;
            for (int i = 1; i < batchSize; i++) {
                long amount = (mask >> ((i - 1) * K_WIDTH)) & K_WIDTH_MASK;
                total += i * amount;
            }
            int best = 0;
            for (int i = 1; i < batchSize; i++) {
                long amount = (mask >> ((i - 1) * K_WIDTH)) & K_WIDTH_MASK;
                if (amount > 0) {
                    int result = dfs(memo, batchSize, mask - (1L << ((i - 1) * K_WIDTH)));
                    if ((total - i) % batchSize == 0) {
                        result++;
                    }
                    best = Math.max(best, result);
                }
            }
            memo.put(mask, best);
        }
        return memo.get(mask);
    }

}
