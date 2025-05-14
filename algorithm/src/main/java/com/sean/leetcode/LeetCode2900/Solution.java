package com.sean.leetcode.LeetCode2900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-15 06:20
 * @Description https://leetcode.cn/problems/longest-unequal-adjacent-groups-subsequence-i
 * 2900. 最长相邻不相等子序列 I
 * 给你一个下标从 0 开始的字符串数组 words ，和一个下标从 0 开始的 二进制 数组 groups ，两个数组长度都是 n 。
 * 你需要从 words 中选出 最长子序列。
 * 如果对于序列中的任何两个连续串，二进制数组 groups 中它们的对应元素不同，则 words 的子序列是不同的。
 * 正式来说，你需要从下标 [0, 1, ..., n - 1] 中选出一个 最长子序列 ，将这个子序列记作长度为 k 的 [i0, i1, ..., ik - 1] ，
 * 对于所有满足 0 <= j < k - 1 的 j 都有 groups[ij] != groups[ij + 1] 。
 * 请你返回一个字符串数组，它是下标子序列 依次 对应 words 数组中的字符串连接形成的字符串数组。
 * 如果有多个答案，返回 任意 一个。
 * 注意：words 中的元素是不同的 。
 * 1 <= n == words.length == groups.length <= 100
 * 1 <= words[i].length <= 10
 * groups[i] 是 0 或 1。
 * words 中的字符串 互不相同 。
 * words[i] 只包含小写英文字母。
 */
public class Solution {

    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        int maxLength = 1, index = 0;
        //以下标i开头的子序列的最大长度为dp[0][i],最长子序列中i的下一个下标为dp[1][i],值为-1，表示没有下一个下标
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], -1);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (groups[i] != groups[j] && dp[0][i] < 1 + dp[0][j]) {
                    dp[0][i] = 1 + dp[0][j];
                    dp[1][i] = j;
                    if (dp[0][i] > maxLength) {
                        maxLength = dp[0][i];
                        index = i;
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        do {
            res.add(words[index]);
            index = dp[1][index];
        } while (index != -1);
        return res;
    }

    public List<String> getLongestSubsequence1(String[] words, int[] groups) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                res.add(words[i]);
            }
        }
        return res;
    }

}
