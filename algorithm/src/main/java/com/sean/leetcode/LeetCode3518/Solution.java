package com.sean.leetcode.LeetCode3518;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-29 07:11
 * @Description: https://leetcode.cn/problems/smallest-palindromic-rearrangement-ii
 * 3518. 最小回文排列 II
 * 给你一个 回文 字符串 s 和一个整数 k。
 * 返回 s 的按字典序排列的 第 k 小 回文排列。
 * 如果不存在 k 个不同的回文排列，则返回空字符串。
 * 注意： 产生相同回文字符串的不同重排视为相同，仅计为一次。
 * 如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个 回文 字符串。
 * 排列 是字符串中所有字符的重排。
 * 如果字符串 a 按字典序小于字符串 b，则表示在第一个不同的位置，a 中的字符比 b 中的对应字符在字母表中更靠前。
 * 如果在前 min(a.length, b.length) 个字符中没有区别，则较短的字符串按字典序更小。
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成。
 * 保证 s 是回文字符串。
 * 1 <= k <= 10^6
 */
public class Solution {

    public String smallestPalindrome(String s, int k) {
        int n = s.length(), m = n / 2;
        int[] total = new int[26];
        for (int i = 0; i < m; i++) {
            total[s.charAt(i) - 'a']++;
        }
        int[] cnt = new int[26];
        long perm = 1;
        int i = m - 1, j = 25;
        for (; i >= 0 && perm < k; i--) {
            while (cnt[j] == total[j]) {
                j--;
            }
            cnt[j]++;
            perm = perm * (m - i) / cnt[j];
        }
        if (perm < k) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (int c = 0; c <= j; c++) {
            for (int l = 0; l < total[c] - cnt[c]; l++) {
                res.append((char) ('a' + c));
            }
        }
        int j0 = j;
        for (i++; i < m; i++) {
            for (j = j0; j < 26; j++) {
                if (cnt[j] == 0) {
                    continue;
                }
                long p = perm * cnt[j] / (m - i);
                if (p >= k) {
                    res.append((char) ('a' + j));
                    cnt[j]--;
                    perm = p;
                    break;
                }
                k -= p;
            }
        }
        StringBuilder rev = new StringBuilder(res).reverse();
        if (n % 2 > 0) {
            res.append(s.charAt(m));
        }
        res.append(rev);
        return res.toString();
    }

}
