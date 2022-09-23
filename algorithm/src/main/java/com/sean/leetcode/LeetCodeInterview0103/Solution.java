package com.sean.leetcode.LeetCodeInterview0103;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-23 12:30
 * @Description: https://leetcode.cn/problems/string-to-url-lcci/?favorite=xb9lfcwi
 * 面试题 01.03. URL化
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。
 * 假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 */
public class Solution {

    public String replaceSpaces(String S, int length) {
        StringBuilder res = new StringBuilder();
        int index = 0;
        int n = S.length();
        while (index < n && index < length) {
            char c = S.charAt(index++);
            if (c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
