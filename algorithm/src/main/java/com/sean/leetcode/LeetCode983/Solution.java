package com.sean.leetcode.LeetCode983;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2024-10-01 19:56
 * @Description https://leetcode.cn/problems/minimum-cost-for-tickets/
 * 983. 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。
 * 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。
 * 每一项是一个从 1 到 365 的整数。
 * 火车票有 三种不同的销售方式 ：
 * 一张 为期一天 的通行证售价为 costs[0] 美元；
 * 一张 为期七天 的通行证售价为 costs[1] 美元；
 * 一张 为期三十天 的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。
 * 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days 按顺序严格递增
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class Solution {

    int[] costs;
    Integer[] dp;
    Set<Integer> set;

    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        dp = new Integer[366];
        set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }
        return dfs(1);
    }

    private int dfs(int i) {
        if (i > 365) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        if (set.contains(i)) {
            dp[i] = Math.min(Math.min(dfs(i + 1) + costs[0], dfs(i + 7) + costs[1]), dfs(i + 30) + costs[2]);
        } else {
            dp[i] = dfs(i + 1);
        }
        return dp[i];
    }

    int[] durations = new int[]{1, 7, 30};
    Integer[] memo;

    public int mincostTickets1(int[] days, int[] costs) {
        memo = new Integer[days.length];
        return preocess(days, costs, 0);
    }

    private int preocess(int[] days, int[] costs, int i) {
        if (i >= days.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        memo[i] = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; k++) {
            while (j < days.length && days[j] < days[i] + durations[k]) {
                j++;
            }
            memo[i] = Math.min(memo[i], preocess(days, costs, j) + costs[k]);
        }
        return memo[i];
    }

}
