package com.sean.leetcode.LeetCode3592;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-01 16:44
 * @Description https://leetcode.cn/problems/inverse-coin-change
 * 3592. 硬币面值还原
 * 给你一个 从 1 开始计数 的整数数组 numWays，其中 numWays[i] 表示使用某些 固定 面值的硬币（每种面值可以使用无限次）凑出总金额 i 的方法数。
 * 每种面值都是一个 正整数 ，并且其值 最多 为 numWays.length。
 * 然而，具体的硬币面值已经 丢失 。
 * 你的任务是还原出可能生成这个 numWays 数组的面值集合。
 * 返回一个按从小到大顺序排列的数组，其中包含所有可能的 唯一 整数面值。
 * 如果不存在这样的集合，返回一个 空 数组。
 * 1 <= numWays.length <= 100
 * 0 <= numWays[i] <= 2 * 10^8
 */
public class Solution {

    public List<Integer> findCoins(int[] numWays) {
        int n = numWays.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int ways = numWays[i - 1];
            //和为i的方案均由小于i的元素组成
            if (ways == dp[i]) {
                continue;
            }
            if (ways - 1 != dp[i]) {
                return new ArrayList<>();
            }
            res.add(i);
            //现在得到了一个大小为i的物品，用i计算完全背包
            for (int j = i; j <= n; j++) {
                dp[j] += dp[j - i];
            }
        }
        return res;
    }

}
