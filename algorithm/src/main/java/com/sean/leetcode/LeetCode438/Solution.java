package com.sean.leetcode.LeetCode438;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-29 13:32
 * @Description https://leetcode.cn/problems/find-all-anagrams-in-a-string
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。
 * 不考虑答案输出的顺序。
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 */
public class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (m > n) {
            return res;
        }
        int[] cnt = new int[26];
        int less = 0;
        for (char c : p.toCharArray()) {
            if (cnt[c - 'a']++ == 0) {
                less++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (--cnt[s.charAt(i) - 'a'] == 0) {
                less--;
            }
            int left = i + 1 - m;
            if (left < 0) {
                continue;
            }
            if (less == 0) {
                res.add(left);
            }
            if (cnt[s.charAt(left) - 'a']++ == 0) {
                less++;
            }
        }
        return res;
    }


}
