package com.sean.leetcode.LeetCode414;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-08 11:49
 * @Description: https://leetcode.cn/problems/third-maximum-number
 * 414. 第三大的数
 * 给定一个整数数组 nums。
 * 返回此数组中 第三大的数 。
 * 如果不存在，则返回数组中 最大 的数。
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class Solution {

    public int thirdMax(int[] nums) {
        Integer first = null, second = null, third = null;
        for (int num : nums) {
            if (first == null || num > first) {
                third = second;
                second = first;
                first = num;
            } else if (first > num && (second == null || num > second)) {
                third = second;
                second = num;
            } else if (second != null && second > num && (third == null || num > third)) {
                third = num;
            }
        }
        return third != null ? third : first;
    }

}
