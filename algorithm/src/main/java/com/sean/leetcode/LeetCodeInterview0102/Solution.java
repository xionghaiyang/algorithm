package com.sean.leetcode.LeetCodeInterview0102;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-23 12:22
 * @Description: https://leetcode.cn/problems/check-permutation-lcci
 * 面试题 01.02. 判定是否互为字符重排
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 */
public class Solution {

    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        if (m != n) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < m; i++) {
            count[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            count[s2.charAt(i) - 'a']--;
        }
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

}
