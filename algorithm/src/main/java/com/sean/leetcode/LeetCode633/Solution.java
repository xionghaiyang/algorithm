package com.sean.leetcode.LeetCode633;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-30 14:11
 * @Description: https://leetcode.cn/problems/sum-of-square-numbers/description/
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 */
public class Solution {

    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

}
