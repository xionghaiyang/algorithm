package com.sean.leetcode.LeetCode1639;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-24 17:46
 * @Description: https://leetcode.cn/problems/number-of-ways-to-form-a-target-string-given-a-dictionary
 * 1639. 通过给定词典构造目标字符串的方案数
 * 给你一个字符串列表 words 和一个目标字符串 target 。
 * words 中所有字符串都 长度相同  。
 * 你的目标是使用给定的 words 字符串列表按照下述规则构造 target ：
 * 从左到右依次构造 target 的每一个字符。
 * 为了得到 target 第 i 个字符（下标从 0 开始），当 target[i] = words[j][k] 时，你可以使用 words 列表中第 j 个字符串的第 k 个字符。
 * 一旦你使用了 words 中第 j 个字符串的第 k 个字符，你不能再使用 words 字符串列表中任意单词的第 x 个字符（x <= k）。
 * 也就是说，所有单词下标小于等于 k 的字符都不能再被使用。
 * 请你重复此过程直到得到目标字符串 target 。
 * 请注意， 在构造目标字符串的过程中，你可以按照上述规定使用 words 列表中 同一个字符串 的 多个字符 。
 * 请你返回使用 words 构造 target 的方案数。
 * 由于答案可能会很大，请对 10^9 + 7 取余 后返回。
 * （译者注：此题目求的是有多少个不同的 k 序列，详情请见示例。）
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words 中所有单词长度相同。
 * 1 <= target.length <= 1000
 * words[i] 和 target 都仅包含小写英文字母。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numWays(String[] words, String target) {
        int m = words[0].length(), n = target.length();
        int[][] cnt = new int[m][26];
        for (String word : words) {
            for (int i = 0; i < m; i++) {
                cnt[i][word.charAt(i) - 'a']++;
            }
        }
        //dp[i][j]:前i个单词匹配j长度的序列数
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1] * cnt[i - 1][target.charAt(j - 1) - 'a']) % MOD;
            }
        }
        return (int) dp[m][n];
    }

}
