package com.sean.leetcode.LeetCode791;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-13 19:54
 * @Description: https://leetcode.cn/problems/custom-sort-string/
 * 791. 自定义字符串排序
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 * 对 s 的字符进行置换，使其与排序的 order 相匹配。
 * 更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
 * 返回 满足这个性质的 s 的任意排列 。
 */
public class Solution {

    public String customSortString1(String order, String s) {
        int[] orderArr = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orderArr[order.charAt(i) - 'a'] = i + 1;
        }
        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr, (c0, c1) -> orderArr[c0 - 'a'] - orderArr[c1 - 'a']);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            res.append(arr[i]);
        }
        return res.toString();
    }

    public String customSortString(String order, String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            while (freq[c - 'a'] > 0) {
                res.append(c);
                freq[c - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                res.append((char) (i + 'a'));
                freq[i]--;
            }
        }
        return res.toString();
    }

}
