package com.sean.leetcode.LeetCode2606;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-23 18:16
 * @Description: https://leetcode.cn/problems/find-the-substring-with-maximum-cost
 * 2606. 找到最大开销的子字符串
 * 给你一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。
 * 子字符串的开销 是一个子字符串中所有字符对应价值之和。
 * 空字符串的开销是 0 。
 * 字符的价值 定义如下：
 * 如果字符不在字符串 chars 中，那么它的价值是它在字母表中的位置（下标从 1 开始）。
 * 比方说，'a' 的价值为 1 ，'b' 的价值为 2 ，以此类推，'z' 的价值为 26 。
 * 否则，如果这个字符在 chars 中的位置为 i ，那么它的价值就是 vals[i] 。
 * 请你返回字符串 s 的所有子字符串中的最大开销。
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 * 1 <= chars.length <= 26
 * chars 只包含小写英文字母，且 互不相同 。
 * vals.length == chars.length
 * -1000 <= vals[i] <= 1000
 */
public class Solution {

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] values = new int[26];
        for (int i = 0; i < 26; i++) {
            values[i] = i + 1;
        }
        int m = chars.length();
        for (int i = 0; i < m; i++) {
            values[chars.charAt(i) - 'a'] = vals[i];
        }
        int res = 0, sum = 0;
        for (char c : s.toCharArray()) {
            if (sum > 0) {
                sum += values[c - 'a'];
            } else {
                sum = values[c - 'a'];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

}
