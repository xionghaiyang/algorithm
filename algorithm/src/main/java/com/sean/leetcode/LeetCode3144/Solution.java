package com.sean.leetcode.LeetCode3144;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-08-28 12:33
 * @Description https://leetcode.cn/problems/minimum-substring-partition-of-equal-character-frequency/
 * 3144. 分割字符频率相等的最少子字符串
 * 给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。
 * 比方说，s == "ababcc" 那么 ("abab", "c", "c") ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，
 * 但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
 * 请你返回 s 最少 能分割成多少个平衡子字符串。
 * 注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
 */
public class Solution {

    private static final int INF = 0x3f3f3f3f;

    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        //dp[i]为将以i结尾的前缀字符串划分平衡字符串的最少个数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int maxCnt = 0;
            for (int j = i; j >= 1; j--) {
                map.put(s.charAt(j - 1), map.getOrDefault(s.charAt(j - 1), 0) + 1);
                maxCnt = Math.max(maxCnt, map.get(s.charAt(j - 1)));
                if (maxCnt * map.size() == (i - j + 1) && dp[j - 1] != INF) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n];
    }

}
