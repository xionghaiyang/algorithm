package com.sean.leetcode.LeetCode3498;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-20 05:59
 * @Description: https://leetcode.cn/problems/reverse-degree-of-a-string
 * 3498. 字符串的反转度
 * 给你一个字符串 s，计算其 反转度。
 * 反转度的计算方法如下：
 * 对于每个字符，将其在 反转 字母表中的位置（'a' = 26, 'b' = 25, ..., 'z' = 1）与其在字符串中的位置（下标从1 开始）相乘。
 * 将这些乘积加起来，得到字符串中所有字符的和。
 * 返回 反转度。
 * 1 <= s.length <= 1000
 * s 仅包含小写字母。
 */
public class Solution {

    public int reverseDegree(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += (26 - s.charAt(i) + 'a') * (i + 1);
        }
        return res;
    }

}
