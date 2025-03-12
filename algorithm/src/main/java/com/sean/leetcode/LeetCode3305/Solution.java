package com.sean.leetcode.LeetCode3305;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-03-12 08:22
 * @Description https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i
 * 3305. 元音辅音字符串计数 I
 * 给你一个字符串 word 和一个 非负 整数 k。
 * 返回 word 的 子字符串 中，每个元音字母（'a'、'e'、'i'、'o'、'u'）至少 出现一次，并且 恰好 包含 k 个辅音字母的子字符串的总数。
 * 5 <= word.length <= 250
 * word 仅由小写英文字母组成。
 * 0 <= k <= word.length - 5
 */
public class Solution {

    public int countOfSubstrings(String word, int k) {
        return process(word, k) - process(word, k + 1);
    }

    //每个元音字母至少出现一次，并且至少包含k个辅音字母的子字符串的总数
    private int process(String word, int k) {
        int n = word.length();
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        int right = 0;
        int cnt = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && (cnt < k || map.size() < 5)) {
                char c = word.charAt(right);
                if (isVowel(c)) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                } else {
                    cnt++;
                }
                right++;
            }
            if (cnt >= k && map.size() == 5) {
                res += n - right + 1;
            }
            char c = word.charAt(left);
            if (isVowel(c)) {
                map.put(c, map.getOrDefault(c, 0) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            } else {
                cnt--;
            }
        }
        return res;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
