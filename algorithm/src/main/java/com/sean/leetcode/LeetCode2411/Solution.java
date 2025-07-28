package com.sean.leetcode.LeetCode2411;

/**
 * @Author xionghaiyang
 * @Date 2025-07-06 18:27
 * @Description https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or
 * 2411. 按位或最大的最小子数组长度
 * 给你一个长度为 n 下标从 0 开始的数组 nums ，数组中所有数字均为非负整数。
 * 对于 0 到 n - 1 之间的每一个下标 i ，你需要找出 nums 中一个 最小 非空子数组，它的起始位置为 i （包含这个位置），同时有 最大 的 按位或运算值 。
 * 换言之，令 Bij 表示子数组 nums[i...j] 的按位或运算的结果，你需要找到一个起始位置为 i 的最小子数组，
 * 这个子数组的按位或运算的结果等于 max(Bik) ，其中 i <= k <= n - 1 。
 * 一个数组的按位或运算值是这个数组里所有数字按位或运算的结果。
 * 请你返回一个大小为 n 的整数数组 answer，其中 answer[i]是开始位置为 i ，按位或运算结果最大，且 最短 子数组的长度。
 * 子数组 是数组里一段连续非空元素组成的序列。
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class Solution {

    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        //计算右端点为i的子数组的或值
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            //子数组的长度至少是1
            res[i] = 1;
            //循环直到nums[j]无法增大，其左侧元素也无法增大
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                //nums[j]增大，现在nums[j]=原数组nums[j]到nums[i]的或值
                nums[j] |= x;
                //nums[j]最后依次增大时的子数组长度就是答案
                res[j] = i - j + 1;
            }
        }
        return res;
    }

}
