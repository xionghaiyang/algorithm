package com.sean.leetcode.LeetCode459;

/**
 * @Author xionghaiyang
 * @Date 2026-04-09 12:20
 * @Description https://leetcode.cn/problems/repeated-substring-pattern
 * 459. 重复的子字符串
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 */
public class Solution {

    public boolean repeatedSubstringPattern(String s) {
        return kmp(s + s, s) != -1;
    }

    //在一个文本串text里，查找模式串pattern是否出现，并返回首次出现的下标
    private int kmp(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int[] next = getNext(pattern);
        int i = 1, j = 0;
        while (i < n - 1) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - j;
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = next[j - 1];
                }
            }
        }
        return -1;
    }

    //next[i]：表示pattern[0...i]这个子串，最长相等前缀和后缀的长度
    //前缀：不包含最后一个字符的所有头部子串
    //后缀：不包含第一个字符的所有尾部子串
    private int[] getNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        //长度为0的子串,next[0] = 0;
        int i = 1, len = 0;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                next[i++] = ++len;
            } else {
                if (len != 0) {
                    len = next[len - 1];
                } else {
                    next[i++] = 0;
                }
            }
        }
        return next;
    }

}
