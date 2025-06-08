package com.sean.leetcode.LeetCode3574;

/**
 * @Author xionghaiyang
 * @Date 2025-06-08 16:34
 * @Description https://leetcode.cn/problems/maximize-subarray-gcd-score/
 * 3574. 最大子数组 GCD 分数
 * 给你一个正整数数组 nums 和一个整数 k。
 * 你最多可以执行 k 次操作。
 * 在每次操作中，你可以选择数组中的一个元素并将其值 翻倍 。
 * 每个元素 最多 只能翻倍一次。
 * 连续 子数组 的 分数 定义为其所有元素的最大公约数 (GCD) 与子数组长度的 乘积 。
 * 你的任务是返回修改后数组中选择一个连续子数组可以获得的最大 分数 。
 * 注意：
 * 子数组 是数组中连续的元素序列。
 * 数组的 最大公约数 (GCD) 是能整除数组所有元素的最大整数。
 * 1 <= n == nums.length <= 1500
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= n
 */
public class Solution {

    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int lowBitMin = Integer.MAX_VALUE;
            int lowBitCnt = 0;
            int g = 0;
            for (int j = i; j >= 0; j--) {
                int x = nums[j];
                int lowBit = x & (-x);
                if (lowBit < lowBitMin) {
                    lowBitMin = lowBit;
                    lowBitCnt = 1;
                } else if (lowBit == lowBitMin) {
                    lowBitCnt++;
                }
                g = gcd(g, x);
                int newG = lowBitCnt <= k ? g * 2 : g;
                res = Math.max(res, (long) newG * (i - j + 1));
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
