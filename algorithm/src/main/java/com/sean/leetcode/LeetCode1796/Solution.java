package com.sean.leetcode.LeetCode1796;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-03 22:19
 * @Description: https://leetcode.cn/problems/second-largest-digit-in-a-string/
 * 1796. 字符串中第二大的数字
 * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
 * 混合字符串 由小写英文字母和数字组成。
 */
public class Solution {

    public int secondHighest(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int n = s.length();
        int first = -1;
        int second = -1;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int cur = s.charAt(i) - '0';
                if (first == -1) {
                    first = cur;
                } else if (cur > first) {
                    second = first;
                    first = cur;
                } else if (cur < first && (second == -1 || cur > second)) {
                    second = cur;
                }
            }
        }
        return second;
    }

}
