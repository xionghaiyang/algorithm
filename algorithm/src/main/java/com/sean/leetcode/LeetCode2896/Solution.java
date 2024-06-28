package com.sean.leetcode.LeetCode2896;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-11 10:50
 * @Description: https://leetcode.cn/problems/apply-operations-to-make-two-strings-equal/
 * 2896. 执行操作使两个字符串相等
 * 给你两个下标从 0 开始的二进制字符串 s1 和 s2 ，两个字符串的长度都是 n ，再给你一个正整数 x 。
 * 你可以对字符串 s1 执行以下操作 任意次 ：
 * 选择两个下标 i 和 j ，将 s1[i] 和 s1[j] 都反转，操作的代价为 x 。
 * 选择满足 i < n - 1 的下标 i ，反转 s1[i] 和 s1[i + 1] ，操作的代价为 1 。
 * 请你返回使字符串 s1 和 s2 相等的 最小 操作代价之和，如果无法让二者相等，返回 -1 。
 * 注意 ，反转字符的意思是将 0 变成 1 ，或者 1 变成 0 。
 */
public class Solution {

    public int minOperations(String s1, String s2, int x) {
        char[] s = s1.toCharArray();
        char[] t = s2.toCharArray();
        int n = s.length, diff = 0;
        for (int i = 0; i < n; i++) {
            diff ^= s[i] ^ t[i];
        }
        if (diff != 0) {
            return -1;
        }
        int[][][] memo = new int[n][n + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(n - 1, 0, 0, memo, s, t, x);
    }

    private int dfs(int i, int j, int preRev, int[][][] memo, char[] s, char[] t, int x) {
        if (i < 0) {
            return j > 0 || preRev > 0 ? Integer.MAX_VALUE / 2 : 0;
        }
        if (memo[i][j][preRev] != -1) {
            return memo[i][j][preRev];
        }
        if ((s[i] == t[i]) == (preRev == 0)) {
            return dfs(i - 1, j, 0, memo, s, t, x);
        }
        int res = Math.min(dfs(i - 1, j + 1, 0, memo, s, t, x) + x, dfs(i - 1, j, 1, memo, s, t, x) + 1);
        if (j > 0) {
            res = Math.min(res, dfs(i - 1, j - 1, 0, memo, s, t, x));
        }
        memo[i][j][preRev] = res;
        return memo[i][j][preRev];
    }

    public int minOperations1(String s1, String s2, int x) {
        if (s1.equals(s2)) {
            return 0;
        }
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                p.add(i);
            }
        }
        if (p.size() % 2 != 0) {
            return -1;
        }
        int f0 = 0, f1 = x;
        for (int i = 1; i < p.size(); i++) {
            int newF = Math.min(f1 + x, f0 + (p.get(i) - p.get(i - 1)) * 2);
            f0 = f1;
            f1 = newF;
        }
        return f1 / 2;
    }

}
