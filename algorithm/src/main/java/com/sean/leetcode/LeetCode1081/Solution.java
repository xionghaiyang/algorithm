package com.sean.leetcode.LeetCode1081;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-19 09:32
 * @Description: https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters
 * 1081. 不同字符的最小子序列
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class Solution {

    public String smallestSubsequence(String s) {
        char[] str = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : str) {
            cnt[c - 'a']++; //统计每个字母的出现次数
        }
        StringBuilder res = new StringBuilder(26); //栈
        boolean[] inRes = new boolean[26];
        for (char c : str) {
            cnt[c - 'a']--;
            if (inRes[c - 'a']) { //res中不能有重复字母
                continue;
            }
            //设x = res.charAt(res.length() - 1)
            //如果c < x，且右边还有x，那么可以把x去掉，因为后面可以重新把x加到res中
            while (res.length() > 0 && c < res.charAt(res.length() - 1) && cnt[res.charAt(res.length() - 1) - 'a'] > 0) {
                inRes[res.charAt(res.length() - 1) - 'a'] = false; //标记栈顶不在res中
                res.deleteCharAt(res.length() - 1);
            }
            inRes[c - 'a'] = true; //把c加到res的末尾
            res.append(c); // 标记c在res中
        }
        return res.toString();
    }

}
