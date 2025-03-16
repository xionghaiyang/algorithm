package com.sean.leetcode.LeetCode2272;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-03-16 08:46
 * @Description https://leetcode.cn/problems/substring-with-largest-variance
 * 2272. 最大波动的子字符串
 * 字符串的 波动 定义为子字符串中出现次数 最多 的字符次数与出现次数 最少 的字符次数之差。
 * 给你一个字符串 s ，它只包含小写英文字母。
 * 请你返回 s 里所有 子字符串的 最大波动 值。
 * 子字符串 是一个字符串的一段连续字符序列。
 * 1 <= s.length <= 10^4
 * s只包含小写英文字母。
 */
public class Solution {

    public int largestVariance(String s) {
        char[] str = s.toCharArray();
        int res = 0;
        //a表示出现次数最多的字母
        for (char a = 'a'; a <= 'z'; a++) {
            //b表示出现次数最少的字母
            for (char b = 'a'; b <= 'z'; b++) {
                if (a == b) {
                    continue;
                }
                //f0表示以s[i]结尾的最大子数组和,包不包含b都可以
                //f1表示以s[i]结尾的、一定包含b的最大子数组和
                int f0 = 0;
                int f1 = Integer.MIN_VALUE;
                for (char c : str) {
                    if (c == a) {
                        f0 = Math.max(f0, 0) + 1;
                        f1++;
                    } else if (c == b) {
                        f0 = Math.max(f0, 0) - 1;
                        f1 = f0;
                    }
                    res = Math.max(res, f1);
                }
            }
        }
        return res;
    }

    public int largestVariance1(String s) {
        int res = 0;
        int[][] f0 = new int[26][26];
        int[][] f1 = new int[26][26];
        for (int[] row : f1) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for (char c : s.toCharArray()) {
            c -= 'a';
            for (int i = 0; i < 26; i++) {
                if (i == c) {
                    continue;
                }
                //假设出现次数最多的字母a = c,更新所有b = i的状态
                f0[c][i] = Math.max(f0[c][i], 0) + 1;
                f1[c][i]++;
                //假设出现最少的字母b = c,更新所有a = i的状态
                f1[i][c] = f0[i][c] = Math.max(f0[i][c], 0) - 1;
                res = Math.max(res, Math.max(f1[c][i], f1[i][c]));
            }
        }
        return res;
    }

    public int largestVariance2(String s) {
        int n = s.length();
        int[][] f0 = new int[26][26];
        int[][] f1 = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(f1[i], -n);
        }
        int res = 0;
        for (char c : s.toCharArray()) {
            c -= 'a';
            for (int i = 0; i < 26; i++) {
                if (i == c) {
                    continue;
                }
                //a = c ,b = i
                f0[c][i]++;
                f1[c][i]++;
                //a = i, b = c;
                f1[i][c] = --f0[i][c];
                f0[i][c] = Math.max(f0[i][c], 0);
                res = Math.max(res, Math.max(f1[c][i], f1[i][c]));
            }
        }
        return res;
    }

}
