package com.sean.leetcode.LeetCode3;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-21 08:28
 * @Description: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int res = 0;
        for (int left = 0, right = -1; left < n; left++) {
            if (left > 0) {
                set.remove(s.charAt(left - 1));
            }
            while (right + 1 < n && !set.contains(s.charAt(right + 1))) {
                set.add(s.charAt(right + 1));
                right++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}
