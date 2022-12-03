package com.sean.leetcode.LeetCode1668;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-03 09:29
 * @Description: https://leetcode.cn/problems/maximum-repeating-substring/
 * 1668. 最大重复子字符串
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。
 * 单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。
 * 如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 */
public class Solution {

    public int maxRepeating1(String sequence, String word) {
        int res = 0;
        for (int i = 0; i < sequence.length(); i++) {
            res = Math.max(res, process1(sequence, word, i));
        }
        return res;
    }

    //返回sequence index位置开始可以匹配word几次
    private int process1(String sequence, String word, int index) {
        int m = sequence.length();
        int n = word.length();
        if (index + n - 1 >= m) {
            return 0;
        }
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (sequence.charAt(index + i) != word.charAt(i)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return 1 + process1(sequence, word, index + n);
        } else {
            return 0;
        }
    }

    public int maxRepeating2(String sequence, String word) {
        int m = sequence.length();
        int n = word.length();
        int[] dp = new int[m + n];
        Arrays.fill(dp, -1);
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(res, process2(sequence, word, i, dp));
        }
        return res;
    }

    //返回sequence index位置开始可以匹配word几次
    private int process2(String sequence, String word, int index, int[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }
        int m = sequence.length();
        int n = word.length();
        if (index + n - 1 >= m) {
            dp[index] = 0;
            return dp[index];
        }
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (sequence.charAt(index + i) != word.charAt(i)) {
                flag = false;
                break;
            }
        }
        int res = 0;
        if (flag) {
            res = 1 + process2(sequence, word, index + n, dp);
        }
        dp[index] = res;
        return res;
    }

    public int maxRepeating(String sequence, String word) {
        int m = sequence.length();
        int n = word.length();
        int[] dp = new int[m + n];
        for (int index = m - n; index >= 0; index--) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (sequence.charAt(index + i) != word.charAt(i)) {
                    flag = false;
                    break;
                }
            }
            int res = 0;
            if (flag) {
                res = 1 + dp[index + n];
            }
            dp[index] = res;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
