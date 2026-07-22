package com.sean.leetcode.LeetCode3513;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-23 06:05
 * @Description: https://leetcode.cn/problems/number-of-unique-xor-triplets-i
 * 3513. 不同 XOR 三元组的数目 I
 * 给你一个长度为 n 的整数数组 nums，其中 nums 是范围 [1, n] 内所有数的 排列 。
 * XOR 三元组 定义为三个元素的异或值 nums[i] XOR nums[j] XOR nums[k]，其中 i <= j <= k。
 * 返回所有可能三元组 (i, j, k) 中 不同 的 XOR 值的数量。
 * 排列 是一个集合中所有元素的重新排列。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= n
 * nums 是从 1 到 n 的整数的一个排列。
 */
public class Solution {

    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        return n <= 2 ? n : 1 << (32 - Integer.numberOfLeadingZeros(n));
    }

}
