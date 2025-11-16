package com.sean.leetcode.LeetCode1513;

/**
 * @Author xionghaiyang
 * @Date 2025-11-16 12:26
 * @Description https://leetcode.cn/problems/number-of-substrings-with-only-1s
 * 1513. 仅含 1 的子串数
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 * 返回所有字符都为 1 的子字符串的数目。
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 * s[i] == '0' 或 s[i] == '1'
 * 1 <= s.length <= 10^5
 */
public class Solution {

    public int numSub(String s) {
        final int mod = 1_000_000_007;
        long res = 0;
        int n = s.length();
        for (int i = 0, cnt = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                cnt = 0;
            } else {
                res += ++cnt;
            }
        }
        return (int) (res % mod);
    }

}
