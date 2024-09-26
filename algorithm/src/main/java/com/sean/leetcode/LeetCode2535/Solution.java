package com.sean.leetcode.LeetCode2535;

/**
 * @Author xionghaiyang
 * @Date 2024-09-26 22:07
 * @Description https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array/
 * 2535. 数组元素和与数字和的绝对差
 * 给你一个正整数数组 nums 。
 * 元素和 是 nums 中的所有元素相加求和。
 * 数字和 是 nums 中每一个元素的每一数位（重复数位需多次求和）相加求和。
 * 返回 元素和 与 数字和 的绝对差。
 * 注意：两个整数 x 和 y 的绝对差定义为 |x - y| 。
 */
public class Solution {

    public int differenceOfSum(int[] nums) {
        int x = 0;
        int y = 0;
        for (int num : nums) {
            x += num;
            while (num > 0) {
                y += (num % 10);
                num /= 10;
            }
        }
        return Math.abs(x - y);
    }

}
