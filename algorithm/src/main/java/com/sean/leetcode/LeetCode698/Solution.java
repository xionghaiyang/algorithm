package com.sean.leetcode.LeetCode698;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-20 08:21
 * @Description: https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
 * 698. 划分为k个相等的子集
 * 给定一个整数数组nums和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */
public class Solution {

    int per;
    int n;
    boolean[] dp;
    int[] nums;

    public boolean canPartitionKSubsets1(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        per = sum / k;
        Arrays.sort(nums);
        this.nums = nums;
        n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        dp = new boolean[1 << n];
        Arrays.fill(dp, true);
        return dfs((1 << n) - 1, 0);
    }

    private boolean dfs(int s, int p) {
        if (s == 0) {
            return true;
        }
        if (!dp[s]) {
            return dp[s];
        }
        dp[s] = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] + p > per) {
                break;
            }
            if (((s >> i) & 1) != 0) {
                if (dfs(s ^ (1 << i), (p + nums[i]) % per)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int per = sum / k;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        boolean[] dp = new boolean[1 << n];
        int[] curSum = new int[1 << n];
        dp[0] = true;
        for (int i = 0; i < 1 << n; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (curSum[i] + nums[j] > per) {
                    break;
                }
                if (((i >> j) & 1) == 0) {
                    int next = i | (1 << j);
                    if (!dp[next]) {
                        curSum[next] = (curSum[i] + nums[j]) % per;
                        dp[next] = true;
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

}
