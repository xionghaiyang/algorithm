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
        int n = s.length();
        int m = p.length();
        List<Integer> res = new ArrayList<>();
        if (n < m) {
            return res;
        }
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < m; i++) {
            cnt[s.charAt(i) - 'a']--;
        }
        if (check(cnt)) {
            res.add(0);
        }
        for (int i = 1; i <= n - m; i++) {
            cnt[s.charAt(i - 1) - 'a']++;
            cnt[s.charAt(m + i - 1) - 'a']--;
            if (cnt[s.charAt(i - 1) - 'a'] == 0 && cnt[s.charAt(m + i - 1) - 'a'] == 0 && check(cnt)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean check(int[] cnt) {
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                return false;
            }
        }
        return true;
    }

}
