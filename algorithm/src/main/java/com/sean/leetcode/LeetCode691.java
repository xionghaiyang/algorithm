package com.sean.leetcode;

import java.util.Arrays;

public class LeetCode691 {


    public static int minStickers(String[] stickers, String target) {
        int m = target.length();
        int[] memo = new int[1 << m];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        int res = process(stickers, target, memo, (1 << m) - 1);
        return res <= m ? res : -1;
    }

    public static int process(String[] strickers, String target, int[] memo, int mask) {
        int m = target.length();
        if (memo[mask] < 0) {
            int res = m + 1;
            for (String stricker : strickers) {
                int left = mask;
                int[] cnt = new int[26];
                for (int i = 0; i < stricker.length(); i++) {
                    cnt[stricker.charAt(i) - 'a']++;
                }
                for (int i = 0; i < target.length(); i++) {
                    char c = target.charAt(i);
                    if (((mask >> i) & 1) == 1 && cnt[c - 'a'] > 0) {
                        cnt[c - 'a']--;
                        left ^= 1 << i;
                    }
                }
                if (left < mask) {
                    res = Math.min(res, process(strickers, target, memo, left) + 1);
                }
            }
            memo[mask] = res;
        }
        return memo[mask];
    }

}
