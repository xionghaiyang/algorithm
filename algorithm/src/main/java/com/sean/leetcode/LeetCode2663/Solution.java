package com.sean.leetcode.LeetCode2663;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2024-06-22 09:26
 * @Description https://leetcode.cn/problems/lexicographically-smallest-beautiful-string/
 * 2663. 字典序最小的美丽字符串
 * 如果一个字符串满足以下条件，则称其为 美丽字符串 ：
 * 它由英语小写字母表的前 k 个字母组成。
 * 它不包含任何长度为 2 或更长的回文子字符串。
 * 给你一个长度为 n 的美丽字符串 s 和一个正整数 k 。
 * 请你找出并返回一个长度为 n 的美丽字符串，该字符串还满足：在字典序大于 s 的所有美丽字符串中字典序最小。
 * 如果不存在这样的字符串，则返回一个空字符串。
 * 对于长度相同的两个字符串 a 和 b ，如果字符串 a 在与字符串 b 不同的第一个位置上的字符字典序更大，则字符串 a 的字典序大于字符串 b 。
 * 例如，"abcd" 的字典序比 "abcc" 更大，因为在不同的第一个位置（第四个字符）上 d 的字典序大于 c
 */
public class Solution {

    public String smallestBeautifulString(String s, int k) {
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            Set<Character> set = new HashSet<>();
            for (int j = 1; j < 3; j++) {
                if (i - j >= 0) {
                    set.add(s.charAt(i - j));
                }
            }
            for (int j = 1; j < 4; j++) {
                if (s.charAt(i) - 'a' + j + 1 <= k && !set.contains((char) (s.charAt(i) + j))) {
                    return generate(s, i, j);
                }
            }
        }
        return "";
    }

    private String generate(String s, int index, int offset) {
        char[] res = s.toCharArray();
        res[index] += offset;
        int n = s.length();
        for (int i = index + 1; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 1; j < 3; j++) {
                if (i - j >= 0) {
                    set.add(res[i - j]);
                }
            }
            for (int j = 0; j < 3; j++) {
                if (!set.contains((char) ('a' + j))) {
                    res[i] = (char) ('a' + j);
                    break;
                }
            }
        }
        return new String(res);
    }

}
