package com.sean.leetcode.LeetCodeInterview0106;

/**
 * @Author xionghaiyang
 * @Date 2025-07-28 17:32
 * @Description https://leetcode.cn/problems/compress-string-lcci
 * 面试题 01.06. 字符串压缩
 * 字符串压缩。
 * 利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。
 * 你可以假设字符串中只包含大小写英文字母（a至z）。
 * 字符串长度在 [0, 50000] 范围内。
 */
public class Solution {

    public String compressString(String S) {
        StringBuilder res = new StringBuilder();
        char[] str = S.toCharArray();
        int i = 0;
        while (i < str.length) {
            char cur = str[i];
            int cnt = 1;
            while (i + 1 < str.length && str[i + 1] == str[i]) {
                cnt++;
                i++;
            }
            res.append(cur).append(cnt);
            i++;
        }
        return res.length() < str.length ? res.toString() : S;
    }

}
