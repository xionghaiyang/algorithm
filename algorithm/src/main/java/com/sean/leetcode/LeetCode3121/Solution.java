package com.sean.leetcode.LeetCode3121;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-27 06:02
 * @Description: https://leetcode.cn/problems/count-the-number-of-special-characters-ii
 * 3121. 统计特殊字母的数量 II
 * 给你一个字符串 word。
 * 如果 word 中同时出现某个字母 c 的小写形式和大写形式，并且 每个 小写形式的 c 都出现在第一个大写形式的 c 之前，则称字母 c 是一个 特殊字母 。
 * 返回 word 中 特殊字母 的数量。
 * 1 <= word.length <= 2 * 10^5
 * word 仅由小写和大写英文字母组成。
 */
public class Solution {

    public int numberOfSpecialChars(String word) {
        int[] has = new int[2];
        int mask = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                has[0] |= 1 << (c - 'a');
                if ((has[1] & (1 << (c - 'a'))) != 0) {
                    mask |= (1 << c - 'a');
                }
            } else {
                has[1] |= 1 << (c - 'A');
            }
        }
        return Integer.bitCount(has[0] & has[1] & (~mask));
    }

}
