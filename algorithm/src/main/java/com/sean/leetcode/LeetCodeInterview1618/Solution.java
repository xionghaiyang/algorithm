package com.sean.leetcode.LeetCodeInterview1618;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 08:04
 * @Description https://leetcode.cn/problems/pattern-matching-lcci
 * 面试题 16.18. 模式匹配
 * 你有两个字符串，即pattern和value。
 * pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。
 * 编写一个方法判断value字符串是否匹配pattern字符串。
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 */
public class Solution {

    public boolean patternMatching(String pattern, String value) {
        int countA = 0, countB = 0;
        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                countA++;
            } else {
                countB++;
            }
        }
        if (countA < countB) {
            int temp = countA;
            countA = countB;
            countB = temp;
            char[] s = pattern.toCharArray();
            for (int i = 0; i < s.length; i++) {
                s[i] = s[i] == 'a' ? 'b' : 'a';
            }
            pattern = String.valueOf(s);
        }
        if (value.length() == 0) {
            return countB == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        for (int lenA = 0; countA * lenA <= value.length(); lenA++) {
            int rest = value.length() - countA * lenA;
            if ((countB == 0 && rest == 0) || (countB != 0 && rest % countB == 0)) {
                int lenB = countB == 0 ? 0 : rest / countB;
                int pos = 0;
                boolean correct = true;
                String valueA = "", valueB = "";
                for (char c : pattern.toCharArray()) {
                    if (c == 'a') {
                        String sub = value.substring(pos, pos + lenA);
                        if (valueA.length() == 0) {
                            valueA = sub;
                        } else if (!valueA.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += lenA;
                    } else {
                        String sub = value.substring(pos, pos + lenB);
                        if (valueB.length() == 0) {
                            valueB = sub;
                        } else if (!valueB.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += lenB;
                    }
                }
                if (correct && !valueA.equals(valueB)) {
                    return true;
                }
            }
        }
        return false;
    }

}
