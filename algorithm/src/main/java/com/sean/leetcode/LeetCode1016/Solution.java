package com.sean.leetcode.LeetCode1016;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-11 08:11
 * @Description: https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/
 * 1016. 子串能表示从 1 到 N 数字的二进制串
 * 给定一个二进制字符串 s 和一个正整数 n，如果对于 [1, n] 范围内的每个整数，
 * 其二进制表示都是 s 的 子字符串 ，就返回 true，否则返回 false 。
 * 子字符串 是字符串中连续的字符序列。
 */
public class Solution {

    public boolean queryString(String s, int n) {
        if (n == 1) {
            return s.indexOf('1') != -1;
        }
        int k = 30;
        while ((1 << k) >= n) {
            k--;
        }
        //2^k <= n < 2^(k+1)
        //[2^(k-1),2^k-1]有2^(k-1)个不同的数需要k个长度
        //[2^k,n] 有n-2^k+1个不同的数需要k+1个长度
        if (s.length() < (1 << (k - 1)) + k - 1 || s.length() < n - (1 << k) + k + 1) {
            return false;
        }
        return help(s, k, 1 << (k - 1), (1 << k) - 1) && help(s, k + 1, 1 << k, n);
    }

    private boolean help(String s, int k, int min, int max) {
        Set<Integer> set = new HashSet<>();
        int t = 0;
        for (int r = 0; r < s.length(); r++) {
            t = t * 2 + (s.charAt(r) - '0');
            if (r >= k) {
                t -= (s.charAt(r - k) - '0') << k;
            }
            if (r >= k - 1 && t >= min && t <= max) {
                set.add(t);
            }
        }
        return set.size() == max - min + 1;
    }

}
