package com.sean.leetcode.LeetCode1863;

/**
 * @Author xionghaiyang
 * @Date 2025-04-05 08:41
 * @Description https://leetcode.cn/problems/sum-of-all-subset-xor-totals
 * 1863. 找出所有子集的异或总和再求和
 * 一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
 * 例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
 * 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。
 * 注意：在本题中，元素 相同 的不同子集应 多次 计数。
 * 数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 */
public class Solution {

    private int res = 0;

    public int subsetXORSum(int[] nums) {
        process(nums, 0, 0);
        return res;
    }

    private void process(int[] nums, int i, int pre) {
        if (i == nums.length) {
            res += pre;
            return;
        }
        process(nums, i + 1, pre ^ nums[i]);
        process(nums, i + 1, pre);
    }

}
