package com.sean.leetcode.LeetCode3335;

/**
 * @Author xionghaiyang
 * @Date 2025-05-13 08:37
 * @Description https://leetcode.cn/problems/total-characters-in-string-after-transformations-i
 * 3335. 字符串转换后的长度 I
 * 给你一个字符串 s 和一个整数 t，表示要执行的 转换 次数。
 * 每次 转换 需要根据以下规则替换字符串 s 中的每个字符：
 * 如果字符是 'z'，则将其替换为字符串 "ab"。
 * 否则，将其替换为字母表中的下一个字符。
 * 例如，'a' 替换为 'b'，'b' 替换为 'c'，依此类推。
 * 返回 恰好 执行 t 次转换后得到的字符串的 长度。
 * 由于答案可能非常大，返回其对 10^9 + 7 取余的结果。
 * 1 <= s.length <= 10^5
 * s 仅由小写英文字母组成。
 * 1 <= t <= 10^5
 */
public class Solution {

    public int lengthAfterTransformations(String s, int t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        while (t > 0) {
            int[] next = new int[26];
            next[0] = cnt[25];
            next[1] = (cnt[25] + cnt[0]) % MOD;
            for (int i = 2; i <= 25; i++) {
                next[i] = cnt[i - 1];
            }
            cnt = next;
            t--;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res = (res + cnt[i]) % MOD;
        }
        return res;
    }

    private static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations1(String s, int t) {
        int[][] f0 = new int[26][1];
        for (int i = 0; i < 26; i++) {
            f0[i][0] = 1;
        }
        int[][] m = new int[26][26];
        for (int i = 1; i < 26; i++) {
            m[i - 1][i] = 1;
        }
        m[25][0] = 1;
        m[25][1] = 1;
        int[][] mt = powMul(m, t, f0);
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        long res = 0;
        for (int i = 0; i < 26; i++) {
            res = (res + (long) mt[i][0] * cnt[i]) % MOD;
        }
        return (int) res;
    }

    //(a^n)*f0
    private int[][] powMul(int[][] a, int n, int[][] f0) {
        int[][] res = f0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    //a * b
    private int[][] mul(int[][] a, int[][] b) {
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] = (int) ((res[i][j] + (long) a[i][k] * b[k][j]) % MOD);
                }
            }
        }
        return res;
    }

}
