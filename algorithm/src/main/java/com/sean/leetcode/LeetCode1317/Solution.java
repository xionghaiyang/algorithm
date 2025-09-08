package com.sean.leetcode.LeetCode1317;

/**
 * @Author xionghaiyang
 * @Date 2025-09-08 11:33
 * @Description https://leetcode.cn/problems/convert-integer-to-the-sum-of-two-no-zero-integers
 * 1317. 将整数转换为两个无零整数的和
 * 「无零整数」是十进制表示中 不含任何 0 的正整数。
 * 给你一个整数 n，请你返回一个 由两个整数组成的列表 [a, b]，满足：
 * a 和 b 都是无零整数
 * a + b = n
 * 题目数据保证至少有一个有效的解决方案。
 * 如果存在多个有效解决方案，你可以返回其中任意一个。
 * 2 <= n <= 10^4
 */
public class Solution {

    public int[] getNoZeroIntegers(int n) {
        int a = 0;
        for (int x = n, base = 1; x > 1; x /= 10, base *= 10) {
            int d = x % 10;
            if (d <= 1) {
                d += 10;
                x -= 10;
            }
            a += d / 2 * base;
        }
        return new int[]{a, n - a};
    }

}
