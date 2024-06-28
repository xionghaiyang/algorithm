package com.sean.leetcode.LeetCode1163;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-24 08:16
 * @Description: https://leetcode.cn/problems/last-substring-in-lexicographical-order/
 * 1163. 按字典序排在最后的子串
 * 给你一个字符串 s ，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
 */
public class Solution {

    public String lastSubstring(String s) {
        int i = 0;
        int j = 1;
        int n = s.length();
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else {
                j += k + 1;
            }
        }
        return s.substring(i);
    }

}
