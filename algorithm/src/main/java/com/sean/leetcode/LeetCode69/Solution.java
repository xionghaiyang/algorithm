package com.sean.leetcode.LeetCode69;

/**
 * @Author xionghaiyang
 * @Date 2025-07-28 13:11
 * @Description https://leetcode.cn/problems/sqrtx
 * 69. x 的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
 * 0 <= x <= 2^31 - 1
 */
public class Solution {

    public int mySqrt(int x) {
        int left = 0, right = Math.min(x, (int) Math.sqrt(Integer.MAX_VALUE)), res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid * mid == x) {
                res = mid;
                break;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else {
                res = mid;
                left = mid + 1;
            }
        }
        return res;
    }

}
