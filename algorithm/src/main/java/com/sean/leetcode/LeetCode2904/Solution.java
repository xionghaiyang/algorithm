package com.sean.leetcode.LeetCode2904;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-11 09:51
 * @Description: https://leetcode.cn/problems/shortest-and-lexicographically-smallest-beautiful-string/
 * 2904. 最短且字典序最小的美丽子字符串
 * 给你一个二进制字符串 s 和一个正整数 k 。
 * 如果 s 的某个子字符串中 1 的个数恰好等于 k ，则称这个子字符串是一个 美丽子字符串 。
 * 令 len 等于 最短 美丽子字符串的长度。
 * 返回长度等于 len 且字典序 最小 的美丽子字符串。
 * 如果 s 中不含美丽子字符串，则返回一个 空 字符串。
 * 对于相同长度的两个字符串 a 和 b ，如果在 a 和 b 出现不同的第一个位置上，a 中该位置上的字符严格大于 b 中的对应字符，则认为字符串 a 字典序 大于 字符串 b 。
 * 例如，"abcd" 的字典序大于 "abcc" ，因为两个字符串出现不同的第一个位置对应第四个字符，而 d 大于 c 。
 */
public class Solution {

    public String shortestBeautifulSubstring(String s, int k) {
        if (s.replace("0", "").length() < k) {
            return "";
        }
        String res = s;
        int n = s.length(), cnt = 0, left = 0;
        for (int right = 0; right < n; right++) {
            cnt += s.charAt(right) - '0';
            while (cnt > k || s.charAt(left) == '0') {
                cnt -= s.charAt(left++) - '0';
            }
            if (cnt == k) {
                String t = s.substring(left, right + 1);
                if (t.length() < res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }

}
