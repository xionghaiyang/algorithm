package com.sean.leetcode.LeetCode2953;

/**
 * @Author xionghaiyang
 * @Date 2025-11-20 19:36
 * @Description https://leetcode.cn/problems/count-complete-substrings
 * 2953. 统计完全子字符串
 * 给你一个字符串 word 和一个整数 k 。
 * 如果 word 的一个子字符串 s 满足以下条件，我们称它是 完全字符串：
 * s 中每个字符 恰好 出现 k 次。
 * 相邻字符在字母表中的顺序 至多 相差 2 。
 * 也就是说，s 中两个相邻字符 c1 和 c2 ，它们在字母表中的位置相差 至多 为 2 。
 * 请你返回 word 中 完全 子字符串的数目。
 * 子字符串 指的是一个字符串中一段连续 非空 的字符序列。
 * 1 <= word.length <= 10^5
 * word 只包含小写英文字母。
 * 1 <= k <= word.length
 */
public class Solution {

    public int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        int res = 0;
        for (int i = 0; i < n; ) {
            int start = i;
            for (i++; i < n && Math.abs(word.charAt(i) - word.charAt(i - 1)) <= 2; i++) ;
            res += f(word.substring(start, i), k);
        }
        return res;
    }

    private int f(String s, int k) {
        char[] str = s.toCharArray();
        int res = 0;
        for (int m = 1; m <= 26 && k * m <= s.length(); m++) {
            int[] cnt = new int[26];
            for (int right = 0; right < s.length(); right++) {
                cnt[str[right] - 'a']++;
                int left = right + 1 - k * m;
                if (left < 0) {
                    continue;
                }
                boolean ok = true;
                for (int i = 0; i < 26; i++) {
                    if (cnt[i] > 0 && cnt[i] != k) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    res++;
                }
                cnt[str[left] - 'a']--;
            }
        }
        return res;
    }

}
