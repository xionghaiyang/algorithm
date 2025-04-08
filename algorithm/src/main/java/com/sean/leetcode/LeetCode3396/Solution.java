package com.sean.leetcode.LeetCode3396;

/**
 * @Author xionghaiyang
 * @Date 2025-04-08 08:32
 * @Description https://leetcode.cn/problems/minimum-number-of-operations-to-make-elements-in-array-distinct
 * 3396. 使数组元素互不相同所需的最少操作次数
 * 给你一个整数数组 nums，你需要确保数组中的元素 互不相同 。
 * 为此，你可以执行以下操作任意次：
 * 从数组的开头移除 3 个元素。
 * 如果数组中元素少于 3 个，则移除所有剩余元素。
 * 注意：空数组也视作为数组元素互不相同。
 * 返回使数组元素互不相同所需的 最少操作次数 。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int[] set = new int[101];
        for (int i = n - 1; i >= 0; i--) {
            if (set[nums[i]] == 0) {
                set[nums[i]] = 1;
            } else if ((i + 1) % 3 == 0) {
                return (i + 1) / 3;
            } else {
                return (i + 1) / 3 + 1;
            }
        }
        return 0;
    }

}
