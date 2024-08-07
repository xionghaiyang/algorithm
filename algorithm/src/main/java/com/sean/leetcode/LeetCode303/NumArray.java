package com.sean.leetcode.LeetCode303;

/**
 * @Author xionghaiyang
 * @Date 2024-05-05 19:44
 * @Description https://leetcode.cn/problems/range-sum-query-immutable/
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，
 * 包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 */
public class NumArray {

    int[] preSum;

    public NumArray(int[] nums) {
        int n = nums.length;
        preSum = new int[n + 1];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return preSum[right];
        }
        return preSum[right] - preSum[left - 1];
    }

}
