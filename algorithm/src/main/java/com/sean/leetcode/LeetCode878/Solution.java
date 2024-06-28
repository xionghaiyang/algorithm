package com.sean.leetcode.LeetCode878;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-22 09:53
 * @Description: https://leetcode.cn/problems/nth-magical-number/
 * 878. 第 N 个神奇数字
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。
 * 因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
 */
public class Solution {

    public int nthMagicalNumber(int n, int a, int b) {
        int mod = 1000000007;
        long left = Math.min(a, b);
        long right = (long) n * Math.min(a, b);
        int c = lcm(a, b);
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = mid / a + mid / b - mid / c;
            if (cnt >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) ((right + 1) % mod);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

}
