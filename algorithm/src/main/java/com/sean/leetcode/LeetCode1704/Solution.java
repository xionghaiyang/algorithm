package com.sean.leetcode.LeetCode1704;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-11 21:57
 * @Description: https://leetcode.cn/problems/determine-if-string-halves-are-alike/
 * 1704. 判断字符串的两半是否相似
 * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
 * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。
 * 注意，s 可能同时含有大写和小写字母。
 * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean halvesAreAlike(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        int count = 0;
        for (int i = 0; i < n / 2; i++) {
            if (set.contains(s.charAt(i))) {
                count++;
            }
        }
        for (int i = n / 2; i < n; i++) {
            if (set.contains(s.charAt(i))) {
                count--;
            }
        }
        return count == 0;
    }

}
