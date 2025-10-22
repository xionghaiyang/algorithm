package com.sean.leetcode.LeetCode1456;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 11:01
 * @Description: https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length
 * 1456. 定长子串中元音的最大数目
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 */
public class Solution {

    public int maxVowels(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int res = 0;
        int vowel = 0;
        for (int i = 0; i < n; i++) {
            if (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u') {
                vowel++;
            }
            int left = i - k + 1;
            if (left < 0) {
                continue;
            }
            res = Math.max(res, vowel);
            if (str[left] == 'a' || str[left] == 'e' || str[left] == 'i' || str[left] == 'o' || str[left] == 'u') {
                vowel--;
            }
        }
        return res;
    }

}
