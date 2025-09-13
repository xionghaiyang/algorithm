package com.sean.leetcode.LeetCode3541;

/**
 * @Author xionghaiyang
 * @Date 2025-09-13 09:38
 * @Description https://leetcode.cn/problems/find-most-frequent-vowel-and-consonant
 * 3541. 找到频率最高的元音和辅音
 * 给你一个由小写英文字母（'a' 到 'z'）组成的字符串 s。
 * 你的任务是找出出现频率 最高 的元音（'a'、'e'、'i'、'o'、'u' 中的一个）和出现频率最高的辅音（除元音以外的所有字母），并返回这两个频率之和。
 * 注意：如果有多个元音或辅音具有相同的最高频率，可以任选其中一个。
 * 如果字符串中没有元音或没有辅音，则其频率视为 0。
 * 一个字母 x 的 频率 是它在字符串中出现的次数。
 * 1 <= s.length <= 100
 * s 只包含小写英文字母
 */
public class Solution {

    public int maxFreqSum(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int cnt0 = 0, cnt1 = 0;
        for (int i = 0; i < 26; i++) {
            if (isVowel((char) (i + 'a'))) {
                cnt0 = Math.max(cnt0, cnt[i]);
            } else {
                cnt1 = Math.max(cnt1, cnt[i]);
            }
        }
        return cnt0 + cnt1;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
