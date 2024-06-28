package com.sean.leetcode.LeetCode691;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 19:58
 * @Description: https://leetcode.cn/problems/stickers-to-spell-word/description/
 * 691. 贴纸拼词
 * 我们有 n 种不同的贴纸。
 * 每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。
 * 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的最小贴纸数量。
 * 如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 */
public class Solution {

    public int minStickers(String[] stickers, String target) {
        int m = target.length();
        int[] dp = new int[1 << m];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int res = process(stickers, target, dp, (1 << m) - 1);
        return res <= m ? res : -1;
    }

    private int process(String[] stickers, String target, int[] dp, int mask) {
        int m = target.length();
        if (dp[mask] < 0) {
            int res = m + 1;
            for (String sticker : stickers) {
                int left = mask;
                int[] cnt = new int[26];
                for (int i = 0; i < sticker.length(); i++) {
                    cnt[sticker.charAt(i) - 'a']++;
                }
                for (int i = 0; i < target.length(); i++) {
                    char c = target.charAt(i);
                    if (((mask >> i) & 1) == 1 && cnt[c - 'a'] > 0) {
                        cnt[c - 'a']--;
                        left ^= 1 << i;
                    }
                }
                if (left < mask) {
                    res = Math.min(res, process(stickers, target, dp, left) + 1);
                }
            }
            dp[mask] = res;
        }
        return dp[mask];
    }

}
