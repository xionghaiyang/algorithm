package com.sean.leetcode.LeetCode2110;

/**
 * @Author xionghaiyang
 * @Date 2025-12-15 11:24
 * @Description https://leetcode.cn/problems/number-of-smooth-descent-periods-of-a-stock
 * 2110. 股票平滑下跌阶段的数目
 * 给你一个整数数组 prices ，表示一支股票的历史每日股价，其中 prices[i] 是这支股票第 i 天的价格。
 * 一个 平滑下降的阶段 定义为：对于 连续一天或者多天 ，每日股价都比 前一日股价恰好少 1 ，这个阶段第一天的股价没有限制。
 * 请你返回 平滑下降阶段 的数目。
 * 1 <= prices.length <= 10^5
 * 1 <= prices[i] <= 10^5
 */
public class Solution {

    public long getDescentPeriods(int[] prices) {
        long res = 1;
        int n = prices.length;
        for (int i = 1, cnt = 1; i < n; i++) {
            if (prices[i] - prices[i - 1] == -1) {
                cnt++;
            } else {
                cnt = 1;
            }
            res += cnt;
        }
        return res;
    }

}
