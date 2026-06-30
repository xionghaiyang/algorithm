package com.sean.leetcode.LeetCode1358;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-30 07:42
 * @Description: https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters
 * 1358. 包含所有三种字符的子字符串数目
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 * 3 <= s.length <= 5 x 10^4
 * s 只包含字符 a，b 和 c 。
 */
public class Solution {

    public int numberOfSubstrings(String s) {
        char[] str = s.toCharArray();
        int[] cnt = new int[3];
        int res = 0, left = 0;
        for (char c : str) {
            cnt[c - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[str[left] - 'a']--;
                left++;
            }
            res += left;
        }
        return res;
    }

}
