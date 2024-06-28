package com.sean.leetcode.LeetCode2182;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-13 12:15
 * @Description: https://leetcode.cn/problems/construct-string-with-repeat-limit/
 * 2182. 构造限制重复的字符串
 * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，使任何字母 连续 出现的次数都不超过 repeatLimit 次。
 * 你不必使用 s 中的全部字符。
 * 返回 字典序最大的 repeatLimitedString 。
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。
 * 如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
 */
public class Solution {

    public String repeatLimitedString(String s, int repeatLimit) {
        int N = 26;
        int[] count = new int[N];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        int cnt = 0;
        int i = N - 1, j = N - 2;
        while (i >= 0 && j >= 0) {
            if (count[i] == 0) {
                cnt = 0;
                i--;
            } else if (cnt < repeatLimit) {
                count[i]--;
                res.append((char) ('a' + i));
                cnt++;
            } else if (j >= i || count[j] == 0) {
                j--;
            } else {
                count[j]--;
                res.append((char) ('a' + j));
                cnt = 0;
            }
        }
        return res.toString();
    }

}
