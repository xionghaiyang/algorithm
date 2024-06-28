package com.sean.leetcode.LeetCodeInterview0101;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-23 12:16
 * @Description: https://leetcode.cn/problems/is-unique-lcci/?favorite=xb9lfcwi
 * 面试题 01.01. 判定字符是否唯一
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 */
public class Solution {

    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        int n = astr.length();
        for (int i = 0; i < n; i++) {
            char c = astr.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

}
