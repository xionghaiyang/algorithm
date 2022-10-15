package com.sean.leetcode.LeetCodeOffer50;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 12:03
 * @Description: https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。
 * 如果没有，返回一个单空格。 s 只包含小写字母。
 */
public class Solution {

    public char firstUniqChar(String s) {
        int n = s.length();
        Map<Character, Boolean> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, !map.containsKey(c));
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.get(c)) {
                return c;
            }
        }
        return ' ';
    }

}
