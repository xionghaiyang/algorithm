package com.sean.leetcode.LeetCode1498;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-29 06:48
 * @Description https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition
 * 1498. 满足条件的子序列数目
 * 给你一个整数数组 nums 和一个整数 target 。
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 * 由于答案可能很大，请将结果对 10^9 + 7 取余后返回。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= target <= 10^6
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int[] pow2 = new int[100_000];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }
    }

    public int numSubseq(int[] nums, int target) {
        init();
        Arrays.sort(nums);
        long res = 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                res = (res + pow2[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }
        return (int) res;
    }

}
