package com.sean.leetcode.LeetCode1144;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-27 08:12
 * @Description: https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/
 * 1144. 递减元素使数组呈锯齿状
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 */
public class Solution {

    public int movesToMakeZigzag(int[] nums) {
        return Math.min(process(nums, 0), process(nums, 1));
    }

    public int process(int[] nums, int start) {
        int res = 0;
        for (int i = start; i < nums.length; i += 2) {
            int a = 0;
            if (i - 1 >= 0) {
                a = Math.max(a, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                a = Math.max(a, nums[i] - nums[i + 1] + 1);
            }
            res += a;
        }
        return res;
    }

}
