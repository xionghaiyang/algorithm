package com.sean.leetcode.LeetCode3618;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 09:34
 * @Description https://leetcode.cn/problems/split-array-by-prime-indices
 * 3618. 根据质数下标分割数组
 * 给你一个整数数组 nums。
 * 根据以下规则将 nums 分割成两个数组 A 和 B：
 * nums 中位于 质数 下标的元素必须放入数组 A。
 * 所有其他元素必须放入数组 B。
 * 返回两个数组和的 绝对 差值：|sum(A) - sum(B)|。
 * 质数 是一个大于 1 的自然数，它只有两个因子，1和它本身。
 * 注意：空数组的和为 0。
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    private static final int MAX = 100_000;
    private static final boolean[] isPrime = new boolean[MAX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public long splitArray(int[] nums) {
        init();
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += isPrime[i] ? nums[i] : -nums[i];
        }
        return Math.abs(res);
    }

}
