package com.sean.leetcode.LeetCode1807;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-12 09:11
 * @Description: https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/
 * 1807. 替换字符串中的括号内容
 * 给你一个字符串 s ，它包含一些括号对，每个括号中包含一个 非空 的键。
 * 比方说，字符串 "(name)is(age)yearsold" 中，有 两个 括号对，分别包含键 "name" 和 "age" 。
 * 你知道许多键对应的值，这些关系由二维字符串数组 knowledge 表示，其中 knowledge[i] = [keyi, valuei] ，表示键 keyi 对应的值为 valuei 。
 * 你需要替换 所有 的括号对。当你替换一个括号对，且它包含的键为 keyi 时，你需要：
 * 将 keyi 和括号用对应的值 valuei 替换。
 * 如果从 knowledge 中无法得知某个键对应的值，你需要将 keyi 和括号用问号 "?" 替换（不需要引号）。
 * knowledge 中每个键最多只会出现一次。s 中不会有嵌套的括号。
 * 请你返回替换 所有 括号对后的结果字符串。
 */
public class Solution {

    public String evaluate(String s, List<List<String>> knowledge) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Map<String, String> map = new HashMap<>();
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder res = new StringBuilder();
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i++);
            if (c == '(') {
                int j = i;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i, j);
                res.append(map.getOrDefault(key, "?"));
                i = ++j;
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
