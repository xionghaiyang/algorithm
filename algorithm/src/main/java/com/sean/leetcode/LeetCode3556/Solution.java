package com.sean.leetcode.LeetCode3556;

import java.util.TreeSet;

/**
 * @Author xionghaiyang
 * @Date 2025-06-07 11:08
 * @Description https://leetcode.cn/problems/sum-of-largest-prime-substrings/
 * 3556. 最大质数子字符串之和
 * 给定一个字符串 s，找出可以由其 子字符串 组成的 3个最大的不同质数 的和。
 * 返回这些质数的 总和 ，如果少于 3 个不同的质数，则返回 所有 不同质数的和。
 * 质数是大于 1 且只有两个因数的自然数：1和它本身。
 * 子字符串 是字符串中的一个连续字符序列。
 * 注意：每个质数即使出现在 多个 子字符串中，也只能计算 一次 。
 * 此外，将子字符串转换为整数时，忽略任何前导零。
 * 1 <= s.length <= 10
 * s 仅由数字组成。
 */
public class Solution {

    public long sumOfLargestPrimes(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            long x = 0;
            for (int j = i; j < n; j++) {
                x = x * 10 + (str[j] - '0');
                if (isPrime(x)) {
                    set.add(x);
                }
            }
        }
        return set.descendingSet()
                .stream()
                .limit(3)
                .mapToLong(Long::longValue)
                .sum();
    }

    private boolean isPrime(long n) {
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n >= 2;
    }

}
