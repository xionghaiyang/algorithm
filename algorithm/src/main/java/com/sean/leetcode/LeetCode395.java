package com.sean.leetcode;

public class LeetCode395 {

    public int longestSubstring(String s, int k) {
        int res = 0;
        int len = s.length();
        for (int i = 1; i <= 26; i++) {
            int[] cnt = new int[26];
            int total = 0;//字符种类数量
            int less = 0;//个数小于k的字符种类数量
            int left = 0;
            int right = 0;
            while (right < len) {
                cnt[s.charAt(right) - 'a']++;
                if (cnt[s.charAt(right) - 'a'] == 1) {
                    total++;
                    less++;
                }
                if (cnt[s.charAt(right) - 'a'] == k) {
                    less--;
                }
                while (total > i) {
                    cnt[s.charAt(left) - 'a']--;
                    if (cnt[s.charAt(left) - 'a'] == k - 1) {
                        less++;
                    }
                    if (cnt[s.charAt(left) - 'a'] == 0) {
                        total--;
                        less--;
                    }
                    left++;
                }
                if (less == 0) {
                    res = Math.max(res, right - left + 1);
                }
                right++;
            }
        }
        return res;
    }

}
