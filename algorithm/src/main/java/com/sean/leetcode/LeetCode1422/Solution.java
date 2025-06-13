package com.sean.leetcode.LeetCode1422;

/**
 * @Author xionghaiyang
 * @Date 2022-08-14 07:34
 * @Description https://leetcode.cn/problems/maximum-score-after-splitting-a-string/
 * 1422. 分割字符串的最大得分
 * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串
 * 分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
 * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
 * 2 <= s.length <= 500
 * 字符串 s 仅由字符 '0' 和 '1' 组成。
 */
public class Solution {

    public int maxScore(String s) {
        int n = s.length();
        int score = 0;
        if (s.charAt(0) == '0') {
            score++;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1') {
                score++;
            }
        }
        int res = score;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                score++;
            } else {
                score--;
            }
            res = Math.max(res, score);
        }
        return res;
    }

}
