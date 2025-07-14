package com.sean.leetcode.LeetCode3136;

/**
 * @Author xionghaiyang
 * @Date 2025-07-15 05:49
 * @Description https://leetcode.cn/problems/valid-word
 * 3136. 有效单词
 * 有效单词 需要满足以下几个条件：
 * 至少 包含 3 个字符。
 * 由数字 0-9 和英文大小写字母组成。（不必包含所有这类字符。）
 * 至少 包含一个 元音字母 。
 * 至少 包含一个 辅音字母 。
 * 给你一个字符串 word 。
 * 如果 word 是一个有效单词，则返回 true ，否则返回 false 。
 * 注意：
 * 'a'、'e'、'i'、'o'、'u' 及其大写形式都属于 元音字母 。
 * 英文中的 辅音字母 是指那些除元音字母之外的字母。
 * 1 <= word.length <= 20
 * word 由英文大写和小写字母、数字、'@'、'#' 和 '$' 组成
 */
public class Solution {

    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }
        boolean hasVowel = false, hasConsonant = false;
        for (char c : word.toCharArray()) {
            if (c == '@' || c == '#' || c == '$') {
                return false;
            }
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                hasVowel = true;
            } else {
                hasConsonant = true;
            }
        }
        return hasVowel && hasConsonant;
    }

}
