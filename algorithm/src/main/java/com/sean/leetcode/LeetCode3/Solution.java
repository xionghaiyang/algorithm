package com.sean.leetcode.LeetCode3;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-21 08:28
 * @Description: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int res = 0;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (index + 1 < n && !set.contains(s.charAt(index + 1))) {
                set.add(s.charAt(index + 1));
                index++;
            }
            res = Math.max(res, index - i + 1);
        }
        return res;
    }

}
