package com.sean.leetcode.LeetCode1759;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-26 15:10
 * @Description: https://leetcode.cn/problems/count-number-of-homogenous-substrings/
 * 1759. 统计同构子字符串的数目
 * 给你一个字符串 s ，返回 s 中 同构子字符串 的数目。
 * 由于答案可能很大，只需返回对 10^9 + 7 取余 后的结果。
 * 同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
 * 子字符串 是字符串中的一个连续字符序列。
 */
public class Solution {

    public int countHomogenous(String s) {
        int n = s.length();
        int mod = 1000000007;
        long res = 0;
        char prev = s.charAt(0);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == prev) {
                cnt++;
            } else {
                res += (long) (cnt + 1) * cnt / 2;
                cnt = 1;
                prev = c;
            }
        }
        res += (long) (cnt + 1) * cnt / 2;
        return (int) (res % mod);
    }

}
