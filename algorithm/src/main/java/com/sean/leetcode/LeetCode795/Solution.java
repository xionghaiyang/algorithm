package com.sean.leetcode.LeetCode795;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-24 09:27
 * @Description: https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum/
 * 795. 区间子数组个数
 * 给你一个整数数组 nums 和两个整数：left 及 right 。
 * 找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 */
public class Solution {

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        int last2 = -1;
        int last1 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                last1 = i;
            } else if (nums[i] > right) {
                last2 = i;
                last1 = -1;
            }
            if (last1 != -1) {
                res += last1 - last2;
            }
        }
        return res;
    }

}
