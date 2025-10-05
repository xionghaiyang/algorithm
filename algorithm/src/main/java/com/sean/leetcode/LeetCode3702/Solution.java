package com.sean.leetcode.LeetCode3702;

/**
 * @Author xionghaiyang
 * @Date 2025-10-05 19:19
 * @Description https://leetcode.cn/problems/longest-subsequence-with-non-zero-bitwise-xor
 * 3702. 按位异或非零的最长子序列
 * 给你一个整数数组 nums。
 * 返回 nums 中 按位异或（XOR）计算结果 非零 的 最长子序列 的长度。
 * 如果不存在这样的 子序列 ，返回 0 。
 * 子序列 是一个 非空 数组，可以通过从原数组中删除一些或不删除任何元素（不改变剩余元素的顺序）派生而来。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class Solution {

    public int longestSubsequence(int[] nums) {
        //是否有非零元素
        boolean hasNonZero = false;
        int xor = 0;
        for (int num : nums) {
            if (num != 0) {
                hasNonZero = true;
            }
            xor ^= num;
        }
        //全为0
        if (!hasNonZero) {
            return 0;
        }
        int res = nums.length;
        if (xor == 0) {
            //去掉非零元素
            res--;
        }
        //如果nums中所有元素的异或和不为0，那么全选即可满足条件
        return res;
    }

}
