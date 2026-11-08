package com.sean.leetcode.LeetCode4106;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-08 17:59
 * @Description: https://leetcode.cn/problems/find-mismatched-segments-between-two-strings
 * 4106. 找出两个字符串的不匹配片段
 * 给你两个长度相同的字符串 s1 和 s2。
 * 不匹配片段 是一个 极大连续 下标区间，其中每个下标处 s1 和 s2 的字符都不相同。
 * 返回一个二维数组，其中包含每个不匹配片段对应的 [sub1, sub2]，sub1 和 sub2 分别是 s1 和 s2 中对应的 子字符串。
 * 请按片段起始下标的 递增 顺序返回。
 * 如果没有不匹配片段，则返回一个空数组。
 * 子字符串 是字符串中一个连续的 非空 字符序列。
 * 1 <= s1.length == s2.length <= 100
 * s1 和 s2 仅由小写英文字母组成。
 */
public class Solution {

    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        List<List<String>> res = new ArrayList<>();
        int n = s1.length(), i = 0;
        while (i < n) {
            while (i < n && s1.charAt(i) == s2.charAt(i)) {
                i++;
            }
            if (i == n) {
                break;
            }
            int j = i;
            while (j < n && s1.charAt(j) != s2.charAt(j)) {
                j++;
            }
            List<String> list = new ArrayList<>();
            list.add(s1.substring(i, j));
            list.add(s2.substring(i, j));
            res.add(list);
            i = j;
        }
        return res;
    }

}
