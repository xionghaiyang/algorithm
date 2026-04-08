package com.sean.leetcode.LeetCode389;

/**
 * @Author xionghaiyang
 * @Date 2026-04-08 20:33
 * @Description https://leetcode.cn/problems/find-the-difference
 * 389. 找不同
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 */
public class Solution {

    public char findTheDifference(String s, String t) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res ^= c;
        }
        for (char c : t.toCharArray()) {
            res ^= c;
        }
        return (char) res;
    }

}
