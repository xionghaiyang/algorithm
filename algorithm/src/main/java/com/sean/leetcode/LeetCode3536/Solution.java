package com.sean.leetcode.LeetCode3536;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-25 06:16
 * @Description: https://leetcode.cn/problems/maximum-product-of-two-digits
 * 3536. 两个数字的最大乘积
 * 给定一个正整数 n。
 * 返回 任意两位数字 相乘所得的 最大 乘积。
 * 注意：如果某个数字在 n 中出现多次，你可以多次使用该数字。
 * 10 <= n <= 10^9
 */
public class Solution {

    public int maxProduct(int n) {
        int first = 0, second = 0;
        while (n > 0) {
            int digit = n % 10;
            if (digit > first) {
                second = first;
                first = digit;
            } else if (digit > second) {
                second = digit;
            }
            n /= 10;
        }
        return first * second;
    }

}
