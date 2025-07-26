package com.sean.leetcode.LeetCode139;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-27 17:05
 * @Description: https://leetcode.cn/problems/word-break
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。
 * 请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅由小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return process(n, maxLength, s, set, dp) == 1;
    }

    private int process(int i, int maxLength, String s, Set<String> set, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        if (i == 0) {
            dp[i] = 1;
            return dp[i];
        }
        for (int j = i - 1; j >= Math.max(i - maxLength, 0); j--) {
            if (set.contains(s.substring(j, i)) && process(j, maxLength, s, set, dp) == 1) {
                dp[i] = 1;
                return dp[i];
            }
        }
        dp[i] = 0;
        return dp[i];
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLength, 0); j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

}
