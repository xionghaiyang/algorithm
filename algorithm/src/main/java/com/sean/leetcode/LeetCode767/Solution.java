package com.sean.leetcode.LeetCode767;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-01 15:02
 * @Description: https://leetcode.cn/problems/reorganize-string/description/
 * 767. 重构字符串
 * 给定一个字符串 s ，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 返回 s 的任意可能的重新排列。若不可行，返回空字符串 "" 。
 */
public class Solution {

    public String reorganizeString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }

}
