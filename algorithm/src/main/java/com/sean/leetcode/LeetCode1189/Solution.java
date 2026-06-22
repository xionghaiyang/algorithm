package com.sean.leetcode.LeetCode1189;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-22 09:48
 * @Description: https://leetcode.cn/problems/maximum-number-of-balloons
 * 1189. “气球” 的最大数量
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。
 * 请你返回最多可以拼凑出多少个单词 "balloon"。
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 */
public class Solution {

    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[5];
        for (char c : text.toCharArray()) {
            if (c == 'b') {
                cnt[0]++;
            } else if (c == 'a') {
                cnt[1]++;
            } else if (c == 'l') {
                cnt[2]++;
            } else if (c == 'o') {
                cnt[3]++;
            } else if (c == 'n') {
                cnt[4]++;
            }
        }
        cnt[2] /= 2;
        cnt[3] /= 2;
        return Arrays.stream(cnt).min().getAsInt();
    }

}
