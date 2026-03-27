package com.sean.leetcode.LeetCode2573;

/**
 * @Author xionghaiyang
 * @Date 2026-03-28 07:04
 * @Description https://leetcode.cn/problems/find-the-string-with-lcp
 * 2573. 找出对应 LCP 矩阵的字符串
 * 对任一由 n 个小写英文字母组成的字符串 word ，我们可以定义一个 n x n 的矩阵，并满足：
 * lcp[i][j] 等于子字符串 word[i,...,n-1] 和 word[j,...,n-1] 之间的最长公共前缀的长度。
 * 给你一个 n x n 的矩阵 lcp 。
 * 返回与 lcp 对应的、按字典序最小的字符串 word 。
 * 如果不存在这样的字符串，则返回空字符串。
 * 对于长度相同的两个字符串 a 和 b ，如果在 a 和 b 不同的第一个位置，字符串 a 的字母在字母表中出现的顺序先于 b 中的对应字母，则认为字符串 a 按字典序比字符串 b 小。
 * 例如，"aabd" 在字典上小于 "aaca" ，因为二者不同的第一位置是第三个字母，而 'b' 先于 'c' 出现。
 * 1 <= n == lcp.length == lcp[i].length <= 1000
 * 0 <= lcp[i][j] <= n
 */
public class Solution {

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    s[j] = c;
                }
            }
            while (i < n && s[i] > 0) {
                i++;
            }
            if (i == n) {
                break;
            }
        }
        if (i < n) {
            return "";
        }
        for (i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int ijLcp = s[i] != s[j] ? 0 : (i == n - 1 || j == n - 1 ? 1 : lcp[i + 1][j + 1] + 1);
                if (lcp[i][j] != ijLcp) {
                    return "";
                }
            }
        }
        return String.valueOf(s);
    }

}
