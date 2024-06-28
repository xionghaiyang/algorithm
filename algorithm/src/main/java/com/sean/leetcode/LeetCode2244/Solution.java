package com.sean.leetcode.LeetCode2244;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-14 18:15
 * @Description https://leetcode.cn/problems/minimum-rounds-to-complete-all-tasks/
 * 2244. 完成所有任务需要的最少轮数
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。
 * 在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 */
public class Solution {

    public int minimumRounds(int[] tasks) {
        int max = 3;
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
            max = Math.max(max, map.get(task));
        }
        int[] dp = new int[max + 1];
        int res = 0;
        dp[1] = -1;
        dp[2] = 1;
        dp[3] = 1;
        for (int n : map.values()) {
            int x = process(dp, n);
            if (x == -1) {
                return -1;
            } else {
                res += x;
            }
        }
        return res;
    }

    private int process(int[] dp, int n) {
        if (dp[n] != 0) {
            return dp[n];
        }
        int res = 1;
        int res1 = process(dp, n - 3);
        int res2 = process(dp, n - 2);
        if (res1 == -1 && res2 == -1) {
            res = -1;
        } else if (res1 == -1) {
            res += res2;
        } else if (res2 == -1) {
            res += res1;
        } else {
            res += Math.min(res1, res2);
        }
        dp[n] = res;
        return dp[n];
    }

    public int minimumRounds1(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (int n : map.values()) {
            if (n == 1) {
                return -1;
            }
            if (n % 3 == 0) {
                res += n / 3;
            } else {
                res += 1 + n / 3;
            }
        }
        return res;
    }

}
