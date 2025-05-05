package com.sean.leetcode.LeetCode1920;

/**
 * @Author xionghaiyang
 * @Date 2025-05-06 06:22
 * @Description https://leetcode.cn/problems/build-array-from-permutation
 * 1920. 基于排列构建数组
 * 给你一个 从 0 开始的排列 nums（下标也从 0 开始）。
 * 请你构建一个 同样长度 的数组 ans ，其中，对于每个 i（0 <= i < nums.length），都满足 ans[i] = nums[nums[i]] 。
 * 返回构建好的数组 ans 。
 * 从 0 开始的排列 nums 是一个由 0 到 nums.length - 1（0 和 nums.length - 1 也包含在内）的不同整数组成的数组。
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] < nums.length
 * nums 中的元素 互不相同
 * 进阶：你能在不使用额外空间的情况下解决此问题吗（即 O(1) 内存）？
 */
public class Solution {

    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[nums[i]];
        }
        return res;
    }

    public int[] buildArray1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x < 0) {
                continue;
            }
            int cur = i;
            while (nums[cur] != i) {
                int next = nums[cur];
                //-x = (~x) +1
                // ~x = -(x+1)  一个正数会变负数
                nums[cur] = ~nums[next];
                cur = next;
            }
            nums[cur] = ~x;
        }
        for (int i = 0; i < n; i++) {
            //x = ~(~x)
            nums[i] = ~nums[i];
        }
        return nums;
    }

}
