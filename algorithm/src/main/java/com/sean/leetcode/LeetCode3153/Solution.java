package com.sean.leetcode.LeetCode3153;

/**
 * @Author xionghaiyang
 * @Date 2024-08-30 12:21
 * @Description https://leetcode.cn/problems/sum-of-digit-differences-of-all-pairs/
 * 3153. 所有数对中数位不同之和
 * 你有一个数组 nums ，它只包含 正 整数，所有正整数的数位长度都 相同 。
 * 两个整数的 数位不同 指的是两个整数 相同 位置上不同数字的数目。
 * 请你返回 nums 中 所有 整数对里，数位不同之和。
 */
public class Solution {

    public long sumDigitDifferences(int[] nums) {
        long res = 0;
        int n = nums.length;
        while (nums[0] > 0) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) {
                cnt[nums[i] % 10]++;
                nums[i] /= 10;
            }
            for (int i = 0; i < 10; i++) {
                res += (long) (n - cnt[i]) * cnt[i];
            }
        }
        return res / 2;
    }

}
