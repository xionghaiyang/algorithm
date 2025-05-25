package com.sean.leetcode.LeetCode2131;

/**
 * @Author xionghaiyang
 * @Date 2025-05-25 07:40
 * @Description https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words
 * 2131. 连接两字母单词得到的最长回文串
 * 给你一个字符串数组 words 。
 * words 中每个元素都是一个包含 两个 小写英文字母的单词。
 * 请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。
 * 每个元素 至多 只能使用一次。
 * 请你返回你能得到的最长回文串的 长度 。
 * 如果没办法得到任何一个回文串，请你返回 0 。
 * 回文串 指的是从前往后和从后往前读一样的字符串。
 * 1 <= words.length <= 10^5
 * words[i].length == 2
 * words[i] 仅包含小写英文字母。
 */
public class Solution {

    public int longestPalindrome(String[] words) {
        int[][] cnt = new int[26][26];
        int res = 0;
        for (String word : words) {
            if (cnt[word.charAt(1) - 'a'][word.charAt(0) - 'a'] > 0) {
                res += 4;
                cnt[word.charAt(1) - 'a'][word.charAt(0) - 'a']--;
            } else {
                cnt[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i][i] > 0) {
                res += 2;
                break;
            }
        }
        return res;
    }

    public int longestPalindrome1(String[] words) {
        int[][] cnt = new int[26][26];
        for (String word : words) {
            cnt[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }
        int res = 0, odd = 0;
        for (int i = 0; i < 26; i++) {
            int c = cnt[i][i];
            res += c - (c & 1);
            odd |= (c & 1);
            for (int j = i + 1; j < 26; j++) {
                res += Math.min(cnt[i][j], cnt[j][i]) << 1;
            }
        }
        return (res + odd) << 1;
    }

}
