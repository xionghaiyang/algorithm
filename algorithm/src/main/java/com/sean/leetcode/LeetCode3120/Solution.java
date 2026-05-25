package com.sean.leetcode.LeetCode3120;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-26 05:43
 * @Description: https://leetcode.cn/problems/count-the-number-of-special-characters-i
 * 3120. 统计特殊字母的数量 I
 * 给你一个字符串 word。
 * 如果 word 中同时存在某个字母的小写形式和大写形式，则称这个字母为 特殊字母 。
 * 返回 word 中 特殊字母 的数量。
 * 1 <= word.length <= 50
 * word 仅由小写和大写英文字母组成。
 */
public class Solution {

    public int numberOfSpecialChars(String word) {
        int[] has = new int[2];
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                has[0] |= 1 << (c - 'a');
            } else {
                has[1] |= 1 << (c - 'A');
            }
        }
        return Integer.bitCount(has[0] & has[1]);
    }

}
