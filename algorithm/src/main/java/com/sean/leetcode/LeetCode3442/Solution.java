package com.sean.leetcode.LeetCode3442;

/**
 * @Author xionghaiyang
 * @Date 2025-06-10 06:21
 * @Description https://leetcode.cn/problems/maximum-difference-between-even-and-odd-frequency-i
 * 3442. 奇偶频次间的最大差值 I
 * 给你一个由小写英文字母组成的字符串 s 。
 * 请你找出字符串中两个字符 a1 和 a2 的出现频次之间的 最大 差值 diff = a1 - a2，这两个字符需要满足：
 * a1 在字符串中出现 奇数次 。
 * a2 在字符串中出现 偶数次 。
 * 返回 最大 差值。
 * 3 <= s.length <= 100
 * s 仅由小写英文字母组成。
 * s 至少由一个出现奇数次的字符和一个出现偶数次的字符组成。
 */
public class Solution {

    public int maxDifference(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int a1 = Integer.MIN_VALUE, a2 = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if ((count[i] & 1) == 0) {
                if (count[i] > 0) {
                    a2 = Math.min(a2, count[i]);
                }
            } else {
                a1 = Math.max(a1, count[i]);
            }
        }
        return a1 - a2;
    }

}
