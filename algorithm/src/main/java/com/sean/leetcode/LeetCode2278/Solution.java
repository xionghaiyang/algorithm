package com.sean.leetcode.LeetCode2278;

/**
 * @Author xionghaiyang
 * @Date 2025-03-31 09:41
 * @Description https://leetcode.cn/problems/percentage-of-letter-in-string
 * 2278. 字母在字符串中的百分比
 * 给你一个字符串 s 和一个字符 letter ，返回在 s 中等于 letter 字符所占的 百分比 ，向下取整到最接近的百分比。
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 * letter 是一个小写英文字母
 */
public class Solution {

    public int percentageLetter(String s, char letter) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter) {
                cnt++;
            }
        }
        return (cnt * 100) / n;
    }

}
