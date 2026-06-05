package com.sean.leetcode.LeetCode2574;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-06 05:53
 * @Description: https://leetcode.cn/problems/left-and-right-sum-differences
 * 2574. 左右元素和的差值
 * 给你一个下标从 0 开始的长度为 n 的整数数组 nums。
 * 定义两个数组 leftSum 和 rightSum，其中：
 * leftSum[i] 是数组 nums 中下标 i 左侧元素之和。
 * 如果不存在对应的元素，leftSum[i] = 0 。
 * rightSum[i] 是数组 nums 中下标 i 右侧元素之和。
 * 如果不存在对应的元素，rightSum[i] = 0 。
 * 返回长度为 n 数组 answer，其中 answer[i] = |leftSum[i] - rightSum[i]|。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }
        for (int i = 0, leftSum = 0; i < n; i++) {
            rightSum -= nums[i];
            res[i] = Math.abs(rightSum - leftSum);
            leftSum += nums[i];
        }
        return res;
    }

}
