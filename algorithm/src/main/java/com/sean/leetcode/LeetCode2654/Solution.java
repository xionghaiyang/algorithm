package com.sean.leetcode.LeetCode2654;

/**
 * @Author xionghaiyang
 * @Date 2025-11-12 17:48
 * @Description https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1
 * 2654. 使数组所有元素变成 1 的最少操作次数
 * 给你一个下标从 0 开始的 正 整数数组 nums 。
 * 你可以对数组执行以下操作 任意 次：
 * 选择一个满足 0 <= i < n - 1 的下标 i ，将 nums[i] 或者 nums[i+1] 两者之一替换成它们的最大公约数。
 * 请你返回使数组 nums 中所有元素都等于 1 的 最少 操作次数。
 * 如果无法让数组全部变成 1 ，请你返回 -1 。
 * 两个正整数的最大公约数指的是能整除这两个数的最大正整数。
 * 2 <= nums.length <= 50
 * 1 <= nums[i] <= 10^6
 */
public class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length, gcdAll = 0, cnt1 = 0;
        for (int num : nums) {
            gcdAll = gcd(gcdAll, num);
            if (num == 1) {
                cnt1++;
            }
        }
        if (gcdAll > 1) {
            return -1;
        }
        if (cnt1 > 0) {
            return n - cnt1;
        }
        int minSize = n;
        for (int i = 0; i < n; i++) {
            int g = 0;
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minSize = Math.min(minSize, j - i + 1);
                    break;
                }
            }
        }
        return minSize + n - 2;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int t = a;
            a = b % a;
            b = t;
        }
        return b;
    }

}
