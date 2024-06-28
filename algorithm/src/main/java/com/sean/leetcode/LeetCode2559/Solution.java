package com.sean.leetcode.LeetCode2559;

/**
 * @Author xionghaiyang
 * @Date 2024-05-06 19:14
 * @Description https://leetcode.cn/problems/count-vowel-strings-in-ranges/
 * 2559. 统计范围内的元音字符串数
 * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
 * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
 * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 */
public class Solution {

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int value = isVowelString(words[i]) ? 1 : 0;
            preSum[i + 1] = preSum[i] + value;
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = preSum[queries[i][1] + 1] - preSum[queries[i][0]];
        }
        return res;
    }

    private boolean isVowelString(String word) {
        return isVowelLetter(word.charAt(0)) && isVowelLetter(word.charAt(word.length() - 1));
    }

    private boolean isVowelLetter(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
