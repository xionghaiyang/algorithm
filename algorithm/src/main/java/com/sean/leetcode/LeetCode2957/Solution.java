package com.sean.leetcode.LeetCode2957;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 17:08
 * @Description: https://leetcode.cn/problems/remove-adjacent-almost-equal-characters/description/
 * 2957. 消除相邻近似相等字符
 * 给你一个下标从 0 开始的字符串 word 。
 * 一次操作中，你可以选择 word 中任意一个下标 i ，将 word[i] 修改成任意一个小写英文字母。
 * 请你返回消除 word 中所有相邻 近似相等 字符的 最少 操作次数。
 * 两个字符 a 和 b 如果满足 a == b 或者 a 和 b 在字母表中是相邻的，那么我们称它们是 近似相等 字符。
 */
public class Solution {

    public int removeAlmostEqualCharacters(String word) {
        if (word == null || word.length() <= 1) {
            return 0;
        }
        int n = word.length();
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (Math.abs(word.charAt(i - 1) - word.charAt(i)) <= 1) {
                //修改word[i],修改次数+1
                res++;
                //已经将word[i]修改为word[i-1]和word[i+1]均不近似相等的字符
                //跳过word[i+1]和word[i]的比较
                i++;
            }
        }
        return res;
    }

}
