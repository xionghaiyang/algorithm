package com.sean.leetcode.LeetCode1297;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-11-16 19:50
 * @Description https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring
 * 1297. 子串的最大出现次数
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s 只包含小写英文字母。
 */
public class Solution {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        char[] str = s.toCharArray();
        Map<String, Integer> strCnt = new HashMap<>();
        int[] charCnt = new int[26];
        int kinds = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int in = str[i] - 'a';
            if (charCnt[in] == 0) {
                kinds++;
            }
            charCnt[in]++;
            int left = i - minSize + 1;
            if (left < 0) {
                continue;
            }
            if (kinds <= maxLetters) {
                String subStr = s.substring(left, left + minSize);
                strCnt.put(subStr, strCnt.getOrDefault(subStr, 0) + 1);
                res = Math.max(res, strCnt.get(subStr));
            }
            int out = str[left] - 'a';
            charCnt[out]--;
            if (charCnt[out] == 0) {
                kinds--;
            }
        }
        return res;
    }

}
