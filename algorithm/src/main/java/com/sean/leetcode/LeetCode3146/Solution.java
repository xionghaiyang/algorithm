package com.sean.leetcode.LeetCode3146;

/**
 * @Author xionghaiyang
 * @Date 2024-08-24 13:30
 * @Description https://leetcode.cn/problems/permutation-difference-between-two-strings/
 * 3146. 两个字符串的排列差
 * 给你两个字符串 s 和 t，每个字符串中的字符都不重复，且 t 是 s 的一个排列。
 * 排列差 定义为 s 和 t 中每个字符在两个字符串中位置的绝对差值之和。
 * 返回 s 和 t 之间的 排列差 。
 * 1 <= s.length <= 26
 * 每个字符在 s 中最多出现一次。
 * t 是 s 的一个排列。
 * s 仅由小写英文字母组成。
 */
public class Solution {

    public int findPermutationDifference(String s, String t) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a'] = i;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            res += Math.abs(cnt[t.charAt(j) - 'a'] - j);
        }
        return res;
    }

}
