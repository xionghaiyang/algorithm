package com.sean.leetcode.LeetCode76;

/**
 * @Author xionghaiyang
 * @Date 2025-05-30 09:58
 * @Description https://leetcode.cn/problems/minimum-window-substring
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。
 * 返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 */
public class Solution {

    public String minWindow(String s, String t) {
        int[] cnt = new int[128];
        for (char c : t.toCharArray()) {
            cnt[c]++;
        }
        int m = s.length();
        int minLength = Integer.MAX_VALUE;
        String res = "";
        for (int left = 0, right = 0; left < m; left++) {
            while (right < m && !check(cnt)) {
                cnt[s.charAt(right)]--;
                right++;
            }
            if (check(cnt) && right - left + 1 < minLength) {
                System.out.println(left + "_" + right);
                minLength = right - left + 1;
                res = s.substring(left, right);
            }
            cnt[s.charAt(left)]++;
        }
        return res;
    }

    private boolean check(int[] cnt) {
        for (int i = 'a'; i <= 'z'; i++) {
            if (cnt[i] > 0) {
                return false;
            }
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (cnt[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public String minWindow1(String s, String t) {
        int m = s.length();
        int resLeft = -1, resRight = m;
        int[] cnt = new int[128];
        int less = 0;
        for (char c : t.toCharArray()) {
            if (cnt[c] == 0) {
                less++;
            }
            cnt[c]++;
        }
        for (int left = 0, right = 0; right < m; right++) {
            char c = s.charAt(right);
            if (--cnt[c] == 0) {
                less--;
            }
            while (less == 0) {
                if (right - left < resRight - resLeft) {
                    resLeft = left;
                    resRight = right;
                }
                char x = s.charAt(left);
                if (cnt[x] == 0) {
                    less++;
                }
                cnt[x]++;
                left++;
            }
        }
        return resLeft < 0 ? "" : s.substring(resLeft, resRight + 1);
    }

}
