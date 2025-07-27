package com.sean.leetcode.LeetCode2044;

/**
 * @Author xionghaiyang
 * @Date 2025-07-28 05:56
 * @Description https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets
 * 2044. 统计按位或能得到最大值的子集数目
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。
 * 如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    private int res = 0;

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        process(nums, 0, max, 0);
        return res;
    }

    private void process(int[] nums, int pre, int max, int i) {
        if (pre == max) {
            res += 1 << (nums.length - i);
            return;
        }
        if (i == nums.length) {
            return;
        }
        process(nums, pre | nums[i], max, i + 1);
        process(nums, pre, max, i + 1);
    }

}
