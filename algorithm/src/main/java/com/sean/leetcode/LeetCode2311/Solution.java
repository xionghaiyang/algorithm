package com.sean.leetcode.LeetCode2311;

/**
 * @Author xionghaiyang
 * @Date 2025-06-26 05:56
 * @Description https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k
 * 2311. 小于等于 K 的最长二进制子序列
 * 给你一个二进制字符串 s 和一个正整数 k 。
 * 请你返回 s 的 最长 子序列的长度，且该子序列对应的 二进制 数字小于等于 k 。
 * 注意：
 * 子序列可以有 前导 0 。
 * 空字符串视为 0 。
 * 子序列 是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。
 * 1 <= s.length <= 1000
 * s[i] 要么是 '0' ，要么是 '1' 。
 * 1 <= k <= 10^9
 */
public class Solution {

    public int longestSubsequence(String s, int k) {
        int n = s.length();
        //k的二进制长度
        int m = 32 - Integer.numberOfLeadingZeros(k);
        if (n < m) {
            return n;
        }
        int sufVal = Integer.parseInt(s.substring(n - m), 2);
        int res = sufVal <= k ? m : m - 1;
        for (int i = 0; i < n - m; i++) {
            res += '1' - s.charAt(i);
        }
        return res;
    }

}
