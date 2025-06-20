package com.sean.leetcode.LeetCode3085;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-21 06:01
 * @Description https://leetcode.cn/problems/minimum-deletions-to-make-string-k-special
 * 3085. 成为 K 特殊字符串需要删除的最少字符数
 * 给你一个字符串 word 和一个整数 k。
 * 如果 |freq(word[i]) - freq(word[j])| <= k 对于字符串中所有下标 i 和 j  都成立，则认为 word 是 k 特殊字符串。
 * 此处，freq(x) 表示字符 x 在 word 中的出现频率，而 |y| 表示 y 的绝对值。
 * 返回使 word 成为 k 特殊字符串 需要删除的字符的最小数量。
 * 1 <= word.length <= 10^5
 * 0 <= k <= 10^5
 * word 仅由小写英文字母组成。
 */
public class Solution {

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        int max = 0;
        for (int i = 0; i < 26; i++) {
            int sum = 0;
            for (int j = i; j < 26; j++) {
                sum += Math.min(freq[j], freq[i] + k);
            }
            max = Math.max(max, sum);
        }
        return word.length() - max;
    }

}
