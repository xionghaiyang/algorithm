package com.sean.leetcode.LeetCode3234;

/**
 * @Author xionghaiyang
 * @Date 2025-11-15 09:42
 * @Description https://leetcode.cn/problems/count-the-number-of-substrings-with-dominant-ones
 * 3234. 统计 1 显著的字符串的数量
 * 给你一个二进制字符串 s。
 * 请你统计并返回其中 1 显著 的 子字符串 的数量。
 * 如果字符串中 1 的数量 大于或等于 0 的数量的 平方，则认为该字符串是一个 1 显著 的字符串 。
 * 1 <= s.length <= 4 * 10^4
 * s 仅包含字符 '0' 和 '1'。
 */
public class Solution {

    public int numberOfSubstrings(String s) {
        int n = s.length();
        //0的下标
        int[] pos0 = new int[n + 1];
        pos0[0] = -1;
        int size = 1;
        //[0,right]中1的个数
        int total1 = 0;
        int res = 0;
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '0') {
                //记录0的下标
                pos0[size++] = right;
            } else {
                total1++;
                //单独计算不含0的子串个数
                res += right - pos0[size - 1];
            }
            for (int i = size - 1; i > 0 && (size - i) * (size - i) <= total1; i--) {
                int p = pos0[i - 1], q = pos0[i];
                int cnt0 = size - i;
                int cnt1 = right - q + 1 - cnt0;
                res += Math.max(Math.max(q - Math.max(cnt0 * cnt0 - cnt1, 0) - p, 0), 0);
            }
        }
        return res;
    }

}
