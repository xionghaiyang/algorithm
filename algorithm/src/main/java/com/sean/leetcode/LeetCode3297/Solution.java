package com.sean.leetcode.LeetCode3297;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-01-09 17:15
 * @Description https://leetcode.cn/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i/
 * 3297. 统计重新排列后包含另一个字符串的子字符串数目 I
 * 给你两个字符串 word1 和 word2 。
 * 如果一个字符串 x 重新排列后，word2 是重排字符串的前缀 ，那么我们称字符串 x 是 合法的 。
 * 请你返回 word1 中 合法子字符串的数目。
 * 1 <= word1.length <= 10^5
 * 1 <= word2.length <= 10^4
 * word1 和 word2 都只包含小写英文字母。
 */
public class Solution {

    public long validSubstringCount(String word1, String word2) {
        int[] diff = new int[26];
        for (char c : word2.toCharArray()) {
            diff[c - 'a']--;
        }
        long res = 0;
        int[] cnt = {(int) Arrays.stream(diff).filter(c -> c < 0).count()};
        int left = 0, right = 0;
        while (left < word1.length()) {
            while (right < word1.length() && cnt[0] > 0) {
                update(diff, word1.charAt(right) - 'a', 1, cnt);
                right++;
            }
            if (cnt[0] == 0) {
                res += word1.length() - right + 1;
            }
            update(diff, word1.charAt(left) - 'a', -1, cnt);
            left++;
        }
        return res;
    }

    private void update(int[] diff, int c, int add, int[] cnt) {
        diff[c] += add;
        if (add == 1 && diff[c] == 0) {
            //diff[c]由-1变为0
            cnt[0]--;
        } else if (add == -1 && diff[c] == -1) {
            //diff[c]由0变为-1
            cnt[0]++;
        }
    }

}
