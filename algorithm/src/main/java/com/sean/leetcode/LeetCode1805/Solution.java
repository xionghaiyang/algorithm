package com.sean.leetcode.LeetCode1805;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-06 08:16
 * @Description: https://leetcode.cn/problems/number-of-different-integers-in-a-string/
 * 1805. 字符串中不同整数的数目
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * 请你用空格替换每个不是数字的字符。
 * 例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。
 * 注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
 */
public class Solution {

    public int numDifferentIntegers(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        int n = word.length();
        Set<String> set = new HashSet<>();
        int i = 0;
        int left;
        boolean flag;
        while (i < n) {
            if (Character.isDigit(word.charAt(i))) {
                left = i++;
                flag = word.charAt(left) == '0';
                while (i < n && Character.isDigit(word.charAt(i))) {
                    if (flag) {
                        left++;
                        flag = word.charAt(left) == '0';
                    }
                    i++;
                }
                set.add(word.substring(left, i));
            } else {
                i++;
            }
        }
        return set.size();
    }

}
