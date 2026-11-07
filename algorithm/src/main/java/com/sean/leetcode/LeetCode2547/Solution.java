package com.sean.leetcode.LeetCode2547;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-07 17:07
 * @Description: https://leetcode.cn/problems/minimum-cost-to-split-an-array
 * 2547. 拆分数组的最小代价
 * 给你一个整数数组 nums 和一个整数 k 。
 * 将数组拆分成一些非空子数组。
 * 拆分的 代价 是每个子数组中的 重要性 之和。
 * 令 trimmed(subarray) 作为子数组的一个特征，其中所有仅出现一次的数字将会被移除。
 * 例如，trimmed([3,1,2,4,3,4]) = [3,4,3,4] 。
 * 子数组的 重要性 定义为 k + trimmed(subarray).length 。
 * 例如，如果一个子数组是 [1,2,3,3,3,4,4] ，trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4] 。
 * 这个子数组的重要性就是 k + 5 。
 * 找出并返回拆分 nums 的所有可行方案中的最小代价。
 * 子数组 是数组的一个连续 非空 元素序列。
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] < nums.length
 * 1 <= k <= 10^9
 */
public class Solution {

    public int minCost(int[] nums, int k) {
        int n = nums.length;
        //f[i]:划分nums的前i个数的最小代价
        int[] f = new int[n + 1];
        byte[] state = new byte[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(state, (byte) 0);
            int unique = 0, min = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                int x = nums[j];
                if (state[x] == 0) {
                    state[x] = 1;
                    unique++;
                } else if (state[x] == 1) {
                    state[x] = 2;
                    unique--;
                }
                min = Math.min(min, f[j] - unique);
            }
            f[i + 1] = k + min;
        }
        return f[n] + n;
    }

}
