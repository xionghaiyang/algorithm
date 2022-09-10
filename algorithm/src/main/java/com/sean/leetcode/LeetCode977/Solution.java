package com.sean.leetcode.LeetCode977;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 19:50
 * @Description: https://leetcode.cn/problems/squares-of-a-sorted-array/?plan=algorithms&plan_progress=zq05vcm
 * 977. 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
public class Solution {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, j = n - 1, index = n - 1; i <= j; ) {
            int fi = nums[i] * nums[i];
            int fj = nums[j] * nums[j];
            if (fi > fj) {
                res[index--] = fi;
                i++;
            } else {
                res[index--] = fj;
                j--;
            }
        }
        return res;
    }

}
