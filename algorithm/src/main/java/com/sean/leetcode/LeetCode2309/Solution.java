package com.sean.leetcode.LeetCode2309;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-29 15:38
 * @Description: https://leetcode.cn/problems/greatest-english-letter-in-upper-and-lower-case/
 * 2309. 兼具大小写的最好英文字母
 * 给你一个由英文字母组成的字符串 s ，请你找出并返回 s 中的 最好 英文字母。
 * 返回的字母必须为大写形式。如果不存在满足条件的字母，则返回一个空字符串。
 * 最好 英文字母的大写和小写形式必须 都 在 s 中出现。
 * 英文字母 b 比另一个英文字母 a 更好 的前提是：英文字母表中，b 在 a 之 后 出现。
 */
public class Solution {

    public String greatestLetter(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            set.add(c);
        }
        for (int i = 25; i >= 0; i--) {
            if (set.contains((char) ('a' + i)) && set.contains((char) ('A' + i))) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "";
    }

}
