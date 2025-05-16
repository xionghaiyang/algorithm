package com.sean.leetcode.LeetCode2901;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-16 08:47
 * @Description https://leetcode.cn/problems/longest-unequal-adjacent-groups-subsequence-ii
 * 2901. 最长相邻不相等子序列 II
 * 给你一个整数 n 和一个下标从 0 开始的字符串数组 words ，和一个下标从 0 开始的数组 groups ，两个数组长度都是 n 。
 * 两个长度相等字符串的 汉明距离 定义为对应位置字符 不同 的数目。
 * 你需要从下标 [0, 1, ..., n - 1] 中选出一个 最长子序列 ，将这个子序列记作长度为 k 的 [i0, i1, ..., ik - 1] ，它需要满足以下条件：
 * 相邻 下标对应的 groups 值 不同。
 * 即，对于所有满足 0 < j + 1 < k 的 j 都有 groups[ij] != groups[ij + 1] 。
 * 对于所有 0 < j + 1 < k 的下标 j ，都满足 words[ij] 和 words[ij + 1] 的长度 相等 ，且两个字符串之间的 汉明距离 为 1 。
 * 请你返回一个字符串数组，它是下标子序列 依次 对应 words 数组中的字符串连接形成的字符串数组。
 * 如果有多个答案，返回任意一个。
 * 子序列 指的是从原数组中删掉一些（也可能一个也不删掉）元素，剩余元素不改变相对位置得到的新的数组。
 * 注意：words 中的字符串长度可能 不相等 。
 * 1 <= n == words.length == groups.length <= 1000
 * 1 <= words[i].length <= 10
 * 1 <= groups[i] <= n
 * words 中的字符串 互不相同 。
 * words[i] 只包含小写英文字母。
 */
public class Solution {

    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        //以下标i开头的子序列的最大长度为dp[0][i],其下一个下标为dp[1][i],值为-1，表示没有下一个下标
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], -1);
        int maxLength = 1, index = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (groups[i] != groups[j] && check(words[i], words[j]) && dp[0][i] < 1 + dp[0][j]) {
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

    private boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        boolean res = false;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (res) {
                    return false;
                }
                res = true;
            }
        }
        return res;
    }

}
