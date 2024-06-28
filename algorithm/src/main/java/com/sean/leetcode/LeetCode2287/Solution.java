package com.sean.leetcode.LeetCode2287;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-13 09:21
 * @Description: https://leetcode.cn/problems/rearrange-characters-to-make-target-string/
 * 2287. 重排字符形成目标字符串
 * 给你两个下标从 0 开始的字符串 s 和 target 。
 * 你可以从 s 取出一些字符并将其重排，得到若干新的字符串。
 * 从 s 中取出字符并重新排列，返回可以形成 target 的 最大 副本数。
 */
public class Solution {

    public int rearrangeCharacters(String s, String target) {
        int[] arr = new int[26];
        int[] str = new int[26];
        for (int i = 0; i < target.length(); i++) {
            arr[target.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            str[s.charAt(i) - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                res = Math.min(res, str[i] / arr[i]);
            }
        }
        return res;
    }

}
