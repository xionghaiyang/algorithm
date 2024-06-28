package com.sean.leetcode.LeetCode1071;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 15:04
 * @Description: https://leetcode.cn/problems/greatest-common-divisor-of-strings/?envType=study-plan-v2&envId=leetcode-75
 * 1071. 字符串的最大公因子
 * 对于字符串 s 和 t，只有在 s = t + ... + t（t 自身连接 1 次或多次）时，
 * 我们才认定 “t 能除尽 s”。
 * 给定两个字符串 str1 和 str2 。
 * 返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
 */
public class Solution {

    public String gcdOfStrings(String str1, String str2) {
        if (!str1.concat(str2).equals(str2.concat(str1))) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int m, int n) {
        int t = m % n;
        while (t != 0) {
            m = n;
            n = t;
            t = m % n;
        }
        return n;
    }

}
