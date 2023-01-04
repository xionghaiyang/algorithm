package com.sean.leetcode.LeetCode2351;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-03 10:11
 * @Description: https://leetcode.cn/problems/first-letter-to-appear-twice/
 * 2351. 第一个出现两次的字母
 * 给你一个由小写英文字母组成的字符串 s ，请你找出并返回第一个出现 两次 的字母。
 * 注意：
 * 如果 a 的 第二次 出现比 b 的 第二次 出现在字符串中的位置更靠前，则认为字母 a 在字母 b 之前出现两次。
 * s 包含至少一个出现两次的字母。
 */
public class Solution {

    public char repeatedCharacter1(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
            } else {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public char repeatedCharacter(String s) {
        int status = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((status & (1 << (s.charAt(i) - 'a'))) == 0) {
                status |= 1 << (s.charAt(i) - 'a');
            } else {
                return s.charAt(i);
            }
        }
        return ' ';
    }

}
