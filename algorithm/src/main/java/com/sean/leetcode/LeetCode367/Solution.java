package com.sean.leetcode.LeetCode367;

/**
 * @Author xionghaiyang
 * @Date 2026-04-08 20:07
 * @Description https://leetcode.cn/problems/valid-perfect-square
 * 367. 有效的完全平方数
 * 给你一个正整数 num 。
 * 如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 完全平方数 是一个可以写成某个整数的平方的整数。
 * 换句话说，它可以写成某个整数和自身的乘积。
 * 不能使用任何内置的库函数，如  sqrt 。
 * 1 <= num <= 2^31 - 1
 */
public class Solution {

    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((long) mid * mid == num) {
                return true;
            } else if ((long) mid * mid > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

}
