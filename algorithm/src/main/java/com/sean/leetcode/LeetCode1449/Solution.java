package com.sean.leetcode.LeetCode1449;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 17:29
 * @Description: https://leetcode.cn/problems/form-largest-integer-with-digits-that-add-up-to-target/description/
 * 1449. 数位成本和为目标值的最大数字
 * 给你一个整数数组 cost 和一个整数 target 。
 * 请你返回满足如下规则可以得到的 最大 整数：
 * 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
 * 总成本必须恰好等于 target 。
 * 添加的数位中没有数字 0 。
 * 由于答案可能会很大，请你以字符串形式返回。
 * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 */
public class Solution {

    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int c : cost) {
            for (int j = c; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - c] + 1);
            }
        }
        if (dp[target] < 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 8, j = target; i >= 0; i--) {
            for (int c = cost[i]; j >= c && dp[j] == dp[j - c] + 1; j -= c) {
                res.append(i + 1);
            }
        }
        return res.toString();
    }

}
