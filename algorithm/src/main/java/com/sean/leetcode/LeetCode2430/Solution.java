package com.sean.leetcode.LeetCode2430;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-07 17:56
 * @Description: https://leetcode.cn/problems/maximum-deletions-on-a-string
 * 2430. 对字母串可执行的最大删除数
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 在一步操作中，你可以：
 * 删除 整个字符串 s ，或者
 * 对于满足 1 <= i <= s.length / 2 的任意 i ，如果 s 中的 前 i 个字母和接下来的 i 个字母 相等 ，删除 前 i 个字母。
 * 例如，如果 s = "ababc" ，那么在一步操作中，你可以删除 s 的前两个字母得到 "abc" ，因为 s 的前两个字母和接下来的两个字母都等于 "ab" 。
 * 返回删除 s 所需的最大操作数。
 * 1 <= s.length <= 4000
 * s 仅由小写英文字母组成
 */
public class Solution {

    public int deleteString(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        for (int i = 1; i < n; i++) {
            if (str[i] != str[0]) {
                break;
            }
            if (i == n - 1) {
                return n;
            }
        }
        //lcp[i][j]:s[i:]和s[j:]的最长公共前缀的长度
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (str[i] == str[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }
        //f[i]:删除后缀s[i:]所需的最大操作数
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; i + j * 2 <= n; j++) {
                //s[i:i+j]=s[i+j:i+j*2]
                if (lcp[i][i + j] >= j) {
                    f[i] = Math.max(f[i], f[i + j]);
                }
            }
            f[i]++;
        }
        return f[0];
    }

}
