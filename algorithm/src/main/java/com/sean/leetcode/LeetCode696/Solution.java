package com.sean.leetcode.LeetCode696;

/**
 * @Author xionghaiyang
 * @Date 2026-02-19 08:22
 * @Description https://leetcode.cn/problems/count-binary-substrings
 * 696. 计数二进制子串
 * 给定一个字符串 s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是成组连续的。
 * 重复出现（不同位置）的子串也要统计它们出现的次数。
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'
 */
public class Solution {

    public int countBinarySubstrings(String s) {
        int n = s.length();
        int[] cnt = new int[n];
        cnt[0] = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt[i] = cnt[i - 1] + 1;
            } else {
                cnt[i] = 1;
            }
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            int x = cnt[i];
            int j = i - x;
            if (j >= 0 && cnt[j] >= x && s.charAt(i) != s.charAt(j)) {
                res++;
            }
        }
        return res;
    }

    public int countBinarySubstrings1(String s) {
        int n = s.length();
        int res = 0, pre = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            cur++;
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                res += Math.min(pre, cur);
                pre = cur;
                cur = 0;
            }
        }
        return res;
    }

}
