package com.sean.leetcode.LeetCode1781;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-12 08:13
 * @Description: https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/
 * 1781. 所有子字符串美丽值之和
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 */
public class Solution {

    public int beautySum(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[s.charAt(j) - 'a']);
                int minFreq = s.length();
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        minFreq = Math.min(minFreq, cnt[k]);
                    }
                }
                res += maxFreq - minFreq;
            }
        }
        return res;
    }

}
