package com.sean.lintcode.LintCode157;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2022-08-28 09:30
 * @Description https://www.lintcode.com/problem/157/?showListFe=true&page=1&pageSize=50
 * 157 · 判断字符串是否没有重复字符
 * 描述
 * 实现一个算法确定字符串中的字符是否均唯一出现
 */
public class Solution {

    public boolean isUnique(String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

}
