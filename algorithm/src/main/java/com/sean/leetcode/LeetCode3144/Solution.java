package com.sean.leetcode.LeetCode3144;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-28 12:33
 * @Description https://leetcode.cn/problems/minimum-substring-partition-of-equal-character-frequency
 * 3144. 分割字符频率相等的最少子字符串
 * 给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。
 * 比方说，s == "ababcc" 那么 ("abab", "c", "c") ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，
 * 但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
 * 请你返回 s 最少 能分割成多少个平衡子字符串。
 * 注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母。
 */
public class Solution {

    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0);
            for (int j = i, k = 0, maxCnt = 0; j >= 0; j--) {
                int index = str[j] - 'a';
                if (++cnt[index] == 1) {
                    k++;
                }
                maxCnt = Math.max(maxCnt, cnt[index]);
                if (i - j + 1 == k * maxCnt) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }

}
