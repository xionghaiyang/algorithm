package com.sean.leetcode.LeetCode3337;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-14 09:01
 * @Description https://leetcode.cn/problems/total-characters-in-string-after-transformations-ii
 * 3337. 字符串转换后的长度 II
 * 给你一个由小写英文字母组成的字符串 s，一个整数 t 表示要执行的 转换 次数，以及一个长度为 26 的数组 nums。
 * 每次 转换 需要根据以下规则替换字符串 s 中的每个字符：
 * 将 s[i] 替换为字母表中后续的 nums[s[i] - 'a'] 个连续字符。
 * 例如，如果 s[i] = 'a' 且 nums[0] = 3，则字符 'a' 转换为它后面的 3 个连续字符，结果为 "bcd"。
 * 如果转换超过了 'z'，则 回绕 到字母表的开头。
 * 例如，如果 s[i] = 'y' 且 nums[24] = 3，则字符 'y' 转换为它后面的 3 个连续字符，结果为 "zab"。
 * 返回 恰好 执行 t 次转换后得到的字符串的 长度。
 * 由于答案可能非常大，返回其对 10^9 + 7 取余的结果。
 * 1 <= s.length <= 10^5
 * s 仅由小写英文字母组成。
 * 1 <= t <= 10^9
 * nums.length == 26
 * 1 <= nums[i] <= 25
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[][] f0 = new int[26][1];
        for (int i = 0; i < 26; i++) {
            f0[i][0] = 1;
        }
        int[][] m = new int[26][26];
        for (int i = 0; i < 26; i++) {
            int num = nums.get(i);
            for (int j = getNextIndex(i); num > 0; j = getNextIndex(j), num--) {
                m[i][j] = 1;
            }
        }
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

    private int getNextIndex(int i) {
        return i == 25 ? 0 : i + 1;
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
