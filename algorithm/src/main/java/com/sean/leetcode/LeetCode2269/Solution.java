package com.sean.leetcode.LeetCode2269;

/**
 * @Author xionghaiyang
 * @Date 2025-03-10 08:04
 * @Description https://leetcode.cn/problems/find-the-k-beauty-of-a-number
 * 2269. 找到一个数字的 K 美丽值
 * 一个整数 num 的 k 美丽值定义为 num 中符合以下条件的 子字符串 数目：
 * 子字符串长度为 k 。
 * 子字符串能整除 num 。
 * 给你整数 num 和 k ，请你返回 num 的 k 美丽值。
 * 注意：
 * 允许有 前缀 0 。
 * 0 不能整除任何值。
 * 一个 子字符串 是一个字符串里的连续一段字符序列。
 * 1 <= num <= 10^9
 * 1 <= k <= num.length （将 num 视为字符串）
 */
public class Solution {

    public int divisorSubstrings(int num, int k) {
        String str = Integer.toString(num);
        int n = str.length();
        int res = 0;
        for (int i = 0; i <= n - k; i++) {
            int cur = Integer.parseInt(str.substring(i, i + k));
            if (cur != 0 && num % cur == 0) {
                res++;
            }
        }
        return res;
    }

}
