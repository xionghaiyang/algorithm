package com.sean.leetcode.LeetCode2789;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-14 15:26
 * @Description: https://leetcode.cn/problems/largest-element-in-an-array-after-merge-operations/
 * 2789. 合并后数组中的最大元素
 * 给你一个下标从 0 开始、由正整数组成的数组 nums 。
 * 你可以在数组上执行下述操作 任意 次：
 * 选中一个同时满足 0 <= i < nums.length - 1 和 nums[i] <= nums[i + 1] 的整数 i 。
 * 将元素 nums[i + 1] 替换为 nums[i] + nums[i + 1] ，并从数组中删除元素 nums[i] 。
 * 返回你可以从最终数组中获得的 最大 元素的值。
 */
public class Solution {

    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long res = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            res = nums[i] <= res ? nums[i] + res : nums[i];
        }
        return res;
    }

}
