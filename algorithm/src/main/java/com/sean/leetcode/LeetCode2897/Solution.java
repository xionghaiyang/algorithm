package com.sean.leetcode.LeetCode2897;

import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-12 06:13
 * @Description: https://leetcode.cn/problems/apply-operations-on-array-to-maximize-sum-of-squares
 * 2897. 对数组执行操作使平方和最大
 * 给你一个下标从 0 开始的整数数组 nums 和一个 正 整数 k 。
 * 你可以对数组执行以下操作 任意次 ：
 * 选择两个互不相同的下标 i 和 j ，同时 将 nums[i] 更新为 (nums[i] AND nums[j]) 且将 nums[j] 更新为 (nums[i] OR nums[j]) ，OR 表示按位 或 运算，AND 表示按位 与 运算。
 * 你需要从最终的数组里选择 k 个元素，并计算它们的 平方 之和。
 * 请你返回你可以得到的 最大 平方和。
 * 由于答案可能会很大，将答案对 10^9 + 7 取余 后返回。
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[30];
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                cnt[i] += (x >> i) & 1;
            }
        }
        long res = 0;
        while (k-- > 0) {
            int x = 0;
            for (int i = 0; i < 30; i++) {
                if (cnt[i] > 0) {
                    cnt[i]--;
                    x |= 1 << i;
                }
            }
            res = (res + (long) x * x) % MOD;
        }
        return (int) res;
    }

}
