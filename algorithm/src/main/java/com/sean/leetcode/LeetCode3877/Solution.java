package com.sean.leetcode.LeetCode3877;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-03 18:18
 * @Description https://leetcode.cn/problems/minimum-removals-to-achieve-target-xor
 * 3877. 达到目标异或值的最少删除次数
 * 给你一个整数数组 nums 和一个整数 target。
 * 你可以从 nums 中移除 任意 数量的元素（可能为零）。
 * 返回使剩余元素的 按位异或和 等于 target 所需的 最小 移除次数。
 * 如果无法达到 target，则返回 -1。
 * 空数组的按位异或和为 0。
 * 1 <= nums.length <= 40
 * 0 <= nums[i] <= 10^4
 * 0 <= target <= 10^4
 */
public class Solution {

    public int minRemovals(int[] nums, int target) {
        int maxXor = 0;
        for (int num : nums) {
            maxXor |= num;
        }
        if (target > maxXor) {
            return -1;
        }
        int[] dp = new int[maxXor + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int num : nums) {
            int[] prevDp = dp.clone();
            for (int x = 0; x <= maxXor; x++) {
                if (prevDp[x] == -1) {
                    continue;
                }
                int newXor = x ^ num;
                int newLen = prevDp[x] + 1;
                if (newLen > dp[newXor]) {
                    dp[newXor] = newLen;
                }
            }
        }
        if (dp[target] == -1) {
            return -1;
        }
        return nums.length - dp[target];
    }

}
