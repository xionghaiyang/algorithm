package com.sean.leetcode.LeetCode1456;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 11:01
 * @Description: https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/?envType=study-plan-v2&envId=leetcode-75
 * 1456. 定长子串中元音的最大数目
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class Solution {

    public int maxVowels(String s, int k) {
        int n = s.length();
        int cur = 0;
        int left = 0, right = 0;
        for (; right < k; right++) {
            cur += check(s.charAt(right));
        }
        int res = cur;
        for (; right < n; left++, right++) {
            cur += check(s.charAt(right)) - check(s.charAt(left));
            res = Math.max(res, cur);
        }
        return res;
    }

    private int check(char c) {
        return "aeiou".indexOf(c) >= 0 ? 1 : 0;
    }

}
