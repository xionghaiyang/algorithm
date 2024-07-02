package com.sean.leetcode.LeetCode3099;

/**
 * @Author xionghaiyang
 * @Date 2024-07-03 06:55
 * @Description https://leetcode.cn/problems/harshad-number/
 * 3099. 哈沙德数
 * 如果一个整数能够被其各个数位上的数字之和整除，则称之为 哈沙德数（Harshad number）。
 * 给你一个整数 x 。
 * 如果 x 是 哈沙德数 ，则返回 x 各个数位上的数字之和，否则，返回 -1 。
 */
public class Solution {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = getSum(x);
        if (x % sum == 0) {
            return sum;
        } else {
            return -1;
        }
    }

    private int getSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

}
