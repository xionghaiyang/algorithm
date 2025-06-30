package com.sean.leetcode.LeetCode3591;

/**
 * @Author xionghaiyang
 * @Date 2025-06-30 15:45
 * @Description https://leetcode.cn/problems/check-if-any-element-has-prime-frequency
 * 3591. 检查元素频次是否为质数
 * 给你一个整数数组 nums。
 * 如果数组中任一元素的 频次 是 质数，返回 true；否则，返回 false。
 * 元素 x 的 频次 是它在数组中出现的次数。
 * 质数是一个大于 1 的自然数，并且只有两个因数：1 和它本身。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class Solution {

    private static final int MAX = 101;
    private static final boolean[] NOT_PRIME = new boolean[MAX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        NOT_PRIME[0] = true;
        NOT_PRIME[1] = true;
        for (int i = 2; i * i < MAX; i++) {
            if (NOT_PRIME[i]) {
                continue;
            }
            for (int j = i * i; j < MAX; j += i) {
                NOT_PRIME[j] = true;
            }
        }
    }

    public boolean checkPrimeFrequency(int[] nums) {
        init();
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        for (int num : cnt) {
            if (!NOT_PRIME[num]) {
                return true;
            }
        }
        return false;
    }

}
