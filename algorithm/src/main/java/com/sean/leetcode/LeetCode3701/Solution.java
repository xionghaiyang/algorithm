package com.sean.leetcode.LeetCode3701;

/**
 * @Author xionghaiyang
 * @Date 2025-10-05 19:15
 * @Description https://leetcode.cn/problems/compute-alternating-sum
 * 3701. 计算交替和
 * 给你一个整数数组 nums。
 * 交替和 定义为：将 nums 中偶数下标位置的元素 相加 ，减去 奇数下标位置的元素。
 * 即：nums[0] - nums[1] + nums[2] - nums[3]...
 * 返回表示 nums 的交替和的整数。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int alternatingSum(int[] nums) {
        int res = 0, sign = 1;
        for (int num : nums) {
            res += sign * num;
            sign *= -1;
        }
        return res;
    }

}
