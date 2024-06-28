package com.sean.leetcode.LeetCode2575;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-07 12:21
 * @Description: https://leetcode.cn/problems/find-the-divisibility-array-of-a-string/
 * 2575. 找出字符串的可整除数组
 * 给你一个下标从 0 开始的字符串 word ，长度为 n ，由从 0 到 9 的数字组成。
 * 另给你一个正整数 m 。
 * word 的 可整除数组 div  是一个长度为 n 的整数数组，并满足：
 * 如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
 * 否则，div[i] = 0
 * 返回 word 的可整除数组。
 */
public class Solution {

    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] res = new int[n];
        long cur = 0;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            cur = (cur * 10 + (c - '0')) % m;
            res[i] = cur == 0 ? 1 : 0;
        }
        return res;
    }

}
