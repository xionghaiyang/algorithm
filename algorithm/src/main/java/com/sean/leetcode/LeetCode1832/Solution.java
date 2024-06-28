package com.sean.leetcode.LeetCode1832;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-13 08:18
 * @Description: https://leetcode.cn/problems/check-if-the-sentence-is-pangram/
 * 1832. 判断句子是否为全字母句
 * 全字母句 指包含英语字母表中每个字母至少一次的句子。
 * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
 * 如果是，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean checkIfPangram1(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return false;
        }
        int n = sentence.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(sentence.charAt(i));
        }
        return set.size() == 26;
    }

    public boolean checkIfPangram(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return false;
        }
        int n = sentence.length();
        int set = 0;
        for (int i = 0; i < n; i++) {
            set |= 1 << (sentence.charAt(i) - 'a');
        }
        return set == (1 << 26) - 1;
    }

}
