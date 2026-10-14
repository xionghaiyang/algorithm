package com.sean.leetcode.LeetCode2262;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-15 06:11
 * @Description: https://leetcode.cn/problems/total-appeal-of-a-string
 * 2262. 字符串的总引力
 * 字符串的 引力 定义为：字符串中 不同 字符的数量。
 * 例如，"abbca" 的引力为 3 ，因为其中有 3 个不同字符 'a'、'b' 和 'c' 。
 * 给你一个字符串 s ，返回 其所有子字符串的总引力 。
 * 子字符串 定义为：字符串中的一个连续字符序列。
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class Solution {

    public long appealSum(String s) {
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int n = s.length();
        long res = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            res += (long) (i - last[c]) * (n - i);
            last[c] = i;
        }
        return res;
    }

    public long appealSum1(String s) {
        int[] last = new int[26];
        Arrays.fill(last, -1);
        long res = 0;
        int n = s.length();
        for (int i = 0, sumG = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            sumG += i - last[c];
            res += sumG;
            last[c] = i;
        }
        return res;
    }

}
