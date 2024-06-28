package com.sean.leetcode.LeetCode1234;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-13 09:24
 * @Description: https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
 * 1234. 替换子串得到平衡字符串
 * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
 * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
 * 请返回待替换子串的最小可能长度。
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 */
public class Solution {

    public int balancedString(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'A']++;
        }
        int partial = n / 4;
        int res = n;
        if (check(cnt, partial)) {
            return 0;
        }
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && !check(cnt, partial)) {
                cnt[s.charAt(r) - 'A']--;
                r++;
            }
            if (!check(cnt, partial)) {
                break;
            }
            res = Math.min(res, r - l);
            cnt[s.charAt(l) - 'A']++;
        }
        return res;
    }

    private boolean check(int[] cnt, int partial) {
        if (cnt['Q' - 'A'] > partial || cnt['W' - 'A'] > partial || cnt['E' - 'A'] > partial || cnt['R' - 'A'] > partial) {
            return false;
        }
        return true;
    }

}
