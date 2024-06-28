package com.sean.leetcode.LeetCode1822;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-27 08:28
 * @Description: https://leetcode.cn/problems/sign-of-the-product-of-an-array/
 * 1822. 数组元素积的符号
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 * 如果 x 是正数，返回 1 。
 * 如果 x 是负数，返回 -1 。
 * 如果 x 是等于 0 ，返回 0 。
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 * 返回 signFunc(product) 。
 */
public class Solution {

    public int arraySign(int[] nums) {
        int res = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                res *= -1;
            }
        }
        return res;
    }

}
