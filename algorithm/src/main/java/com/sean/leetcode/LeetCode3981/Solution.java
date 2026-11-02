package com.sean.leetcode.LeetCode3981;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-02 18:43
 * @Description: https://leetcode.cn/problems/count-distinct-ways-to-form-target-from-two-strings
 * 3981. 统计从两个字符串形成目标字符串的不同方案数
 * 给你三个字符串 word1、word2 和 target。
 * 你的任务是计算从 word1 和 word2 中选择字符以形成 target 的方案数，需满足以下条件：
 * 对于 target 中的每个字符，从 word1 或 word2 中选择一个匹配的字符。
 * 从 word1 中选择的下标必须是 严格 递增的。
 * 从 word2 中选择的下标必须是 严格 递增的。
 * 必须从 word1 和 word2 两者 中 至少 各选择一个字符。
 * 如果对于 target 中的 至少 一个位置，选择的字符来自不同的字符串或不同的下标，则认为两种方案是不同的。
 * 返回方案数。
 * 由于答案可能非常大，请返回它对 10^9 + 7 取余 后的结果。
 * 1 <= word1.length, word2.length, target.length <= 100
 * word1、word2 和 target 仅由小写英文字母组成。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int interleaveCharacters(String word1, String word2, String target) {
        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray(), t = target.toCharArray();
        int m1 = w1.length, m2 = w2.length, n = t.length;
        int[][][] memo = new int[n][m1 + 1][m2 + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m1; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }
        long res = dfs(w1, w2, t, memo, n - 1, m1 - 1, m2 - 1);
        res -= dfs(w1, w2, t, memo, n - 1, m1 - 1, -1);
        res -= dfs(w1, w2, t, memo, n - 1, -1, m2 - 1);
        return (int) ((res % MOD + MOD) % MOD);
    }

    private int dfs(char[] word1, char[] word2, char[] target, int[][][] memo, int i, int j, int k) {
        if (j < -1 || k < -1 || j + k + 1 < i) {
            return 0;
        }
        if (i < 0) {
            return 1;
        }
        if (memo[i][j + 1][k + 1] != Integer.MIN_VALUE) {
            return memo[i][j + 1][k + 1];
        }
        long res = (long) dfs(word1, word2, target, memo, i, j - 1, k) + dfs(word1, word2, target, memo, i, j, k - 1) - dfs(word1, word2, target, memo, i, j - 1, k - 1);
        if (j >= 0 && word1[j] == target[i]) {
            res += dfs(word1, word2, target, memo, i - 1, j - 1, k) - dfs(word1, word2, target, memo, i - 1, j - 1, k - 1);
        }
        if (k >= 0 && word2[k] == target[i]) {
            res += dfs(word1, word2, target, memo, i - 1, j, k - 1) - dfs(word1, word2, target, memo, i - 1, j - 1, k - 1);
        }
        return memo[i][j + 1][k + 1] = (int) (res % MOD);
    }

}
