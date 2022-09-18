package com.sean.leetcode.LeetCode1624;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2022-09-17 11:57
 * @Description https://leetcode.cn/problems/largest-substring-between-two-equal-characters/
 * 1624. 两个相同字符之间的最长子字符串
 * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。
 * 如果不存在这样的子字符串，返回 -1 。
 * 子字符串 是字符串中的一个连续字符序列。
 */
public class Solution {

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int res = -1;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                res = Math.max(res, i - map.get(ch) - 1);
            } else {
                map.put(ch, i);
            }
        }
        return res;
    }

}
