package com.sean.leetcode.LeetCode345;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 15:55
 * @Description: https://leetcode.cn/problems/reverse-vowels-of-a-string/?envType=study-plan-v2&envId=leetcode-75
 * 345. 反转字符串中的元音字母
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
 */
public class Solution {

    public String reverseVowels1(String s) {
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
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                res.append(stack.pop());
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && !isVoewl(arr[i])) {
                i++;
            }
            while (j > 0 && !isVoewl(arr[j])) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    private boolean isVoewl(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    private void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
