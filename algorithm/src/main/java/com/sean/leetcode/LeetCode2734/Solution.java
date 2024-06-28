package com.sean.leetcode.LeetCode2734;

/**
 * @Author xionghaiyang
 * @Date 2024-06-27 10:22
 * @Description https://leetcode.cn/problems/lexicographically-smallest-string-after-substring-operation/
 * 2734. 执行子串操作后的字典序最小字符串
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 在一步操作中，你可以完成以下行为：
 * 选择 s 的任一非空子字符串，可能是整个字符串，接着将字符串中的每一个字符替换为英文字母表中的前一个字符。
 * 例如，'b' 用 'a' 替换，'a' 用 'z' 替换。
 * 返回执行上述操作 恰好一次 后可以获得的 字典序最小 的字符串。
 * 子字符串 是字符串中的一个连续字符序列。
 * 现有长度相同的两个字符串 x 和 字符串 y ，在满足 x[i] != y[i] 的第一个位置 i 上，如果  x[i] 在字母表中先于 y[i] 出现，则认为字符串 x 比字符串 y 字典序更小 。
 */
public class Solution {

    public String smallestString(String s) {
        int n = s.length();
        int indexOfFirstNonA = findFirstNonA(s);
        if (indexOfFirstNonA == n) {
            StringBuilder res = new StringBuilder(s);
            res.setCharAt(n - 1, 'z');
            return res.toString();
        }
        int indexOfFirstA_AfterFirstNonA = findFirstA_AfterNonA(s, indexOfFirstNonA);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (i >= indexOfFirstNonA && i < indexOfFirstA_AfterFirstNonA) {
                res.append((char) (c - 1));
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    private int findFirstNonA(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != 'a') {
                return i;
            }
        }
        return n;
    }

    private int findFirstA_AfterNonA(String s, int firstNonA) {
        int n = s.length();
        for (int i = firstNonA; i < n; i++) {
            if (s.charAt(i) == 'a') {
                return i;
            }
        }
        return n;
    }

}
