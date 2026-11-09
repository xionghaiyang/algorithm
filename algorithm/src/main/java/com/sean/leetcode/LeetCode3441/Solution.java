package com.sean.leetcode.LeetCode3441;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-09 17:15
 * @Description: https://leetcode.cn/problems/minimum-cost-good-caption
 * 3441. 变成好标题的最少代价
 * 给你一个长度为 n 的字符串 caption 。
 * 如果字符串中 每一个 字符都位于连续出现 至少 3 次 的组中，那么我们称这个字符串是 好 标题。
 * 比方说：
 * "aaabbb" 和 "aaaaccc" 都是 好 标题。
 * "aabbb" 和 "ccccd" 都 不是 好标题。
 * 你可以对字符串执行以下操作 任意 次：
 * 选择一个下标 i（其中 0 <= i < n ）然后将该下标处的字符变为：
 * 该字符在字母表中 前 一个字母（前提是 caption[i] != 'a' ）
 * 该字符在字母表中 后 一个字母（caption[i] != 'z' ）
 * 你的任务是用 最少 操作次数将 caption 变为 好 标题。
 * 如果存在 多种 好标题，请返回它们中 字典序最小 的一个。
 * 如果 无法 得到好标题，请你返回一个空字符串 "" 。
 * 在字符串 a 和 b 中，如果两个字符串第一个不同的字符处，字符串 a 的字母比 b 的字母在字母表里出现的顺序更早，那么我们称字符串 a 的 字典序 比 b 小 。
 * 如果两个字符串前 min(a.length, b.length) 个字符都相同，那么较短的一个字符串字典序比另一个字符串小。
 * 1 <= caption.length <= 5 * 10^4
 * caption 只包含小写英文字母。
 */
public class Solution {

    public String minCostGoodCaption(String caption) {
        int n = caption.length();
        if (n < 3) {
            return "";
        }
        char[] str = caption.toCharArray();
        int[][] f = new int[n + 1][26];
        int[] minJ = new int[n + 1];
        int[][] next = new int[n + 1][26];
        for (int i = n - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 26; j++) {
                int res = f[i + 1][j] + Math.abs(str[i] - 'a' - j);
                int res2 = i <= n - 6 ? f[i + 3][minJ[i + 3]] + Math.abs(str[i] - 'a' - j) + Math.abs(str[i + 1] - 'a' - j) + Math.abs(str[i + 2] - 'a' - j) : Integer.MAX_VALUE;
                if (res2 < res || (res2 == res && minJ[i + 3] < j)) {
                    res = res2;
                    next[i][j] = minJ[i + 3];
                } else {
                    next[i][j] = j;
                }
                f[i][j] = res;
                if (res < min) {
                    min = res;
                    minJ[i] = j;
                }
            }
        }
        char[] res = new char[n];
        int i = 0, j = minJ[0];
        while (i < n) {
            res[i] = (char) ('a' + j);
            int k = next[i][j];
            if (k == j) {
                i++;
            } else {
                res[i + 2] = res[i + 1] = res[i];
                i += 3;
                j = k;
            }
        }
        return String.valueOf(res);
    }

}
