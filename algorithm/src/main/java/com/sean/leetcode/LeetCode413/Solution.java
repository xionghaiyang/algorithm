package com.sean.leetcode.LeetCode413;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-27 17:22
 * @Description: https://leetcode.cn/problems/arithmetic-slices/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 */
public class Solution {

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int d = nums[0] - nums[1];
        int res = 0;
        int t = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i] == d) {
                t++;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            res += t;
        }
        return res;
    }

}
