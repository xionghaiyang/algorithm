package com.sean.leetcode.LeetCode1957;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 06:49
 * @Description https://leetcode.cn/problems/delete-characters-to-make-fancy-string
 * 1957. 删除字符使字符串变好
 * 一个字符串如果没有 三个连续 相同字符，那么它就是一个 好字符串 。
 * 给你一个字符串 s ，请你从 s 删除 最少 的字符，使它变成一个 好字符串 。
 * 请你返回删除后的字符串。
 * 题目数据保证答案总是 唯一的 。
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 */
public class Solution {

    public String makeFancyString(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0, pre = s.charAt(0), cnt = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == pre) {
                if (++cnt == 3) {
                    cnt--;
                } else {
                    res.append(c);
                }
            } else {
                res.append(c);
                pre = c;
                cnt = 1;
            }
        }
        return res.toString();
    }

}
