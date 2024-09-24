package com.sean.leetcode.LeetCode2207;

/**
 * @Author xionghaiyang
 * @Date 2024-09-24 14:48
 * @Description https://leetcode.cn/problems/maximize-number-of-subsequences-in-a-string/
 * 2207. 字符串中最多数目的子序列
 * 给你一个下标从 0 开始的字符串 text 和另一个下标从 0 开始且长度为 2 的字符串 pattern ，两者都只包含小写英文字母。
 * 你可以在 text 中任意位置插入 一个 字符，这个插入的字符必须是 pattern[0] 或者 pattern[1] 。
 * 注意，这个字符可以插入在 text 开头或者结尾的位置。
 * 请你返回插入一个字符后，text 中最多包含多少个等于 pattern 的 子序列 。
 * 子序列 指的是将一个字符串删除若干个字符后（也可以不删除），剩余字符保持原本顺序得到的字符串。
 * 1 <= text.length <= 10^5
 * pattern.length == 2
 * text 和 pattern 都只包含小写英文字母。
 */
public class Solution {

    public long maximumSubsequenceCount(String s, String pattern) {
        long res = 0;
        int cnt0 = 0, cnt1 = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == pattern.charAt(1)) {
                res += cnt0;
                cnt1++;
            }
            if (c == pattern.charAt(0)) {
                cnt0++;
            }
        }
        return res + Math.max(cnt0, cnt1);
    }

}
