package com.sean.leetcode.LeetCode2645;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-11 09:33
 * @Description: https://leetcode.cn/problems/minimum-additions-to-make-valid-string/
 * 2645. 构造有效字符串的最少插入数
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
 * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
 */
public class Solution {

    public int addMinimum(String word) {
        int n = word.length();
        int group = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i - 1) >= word.charAt(i)) {
                group++;
            }
        }
        return group * 3 - n;
    }

}
