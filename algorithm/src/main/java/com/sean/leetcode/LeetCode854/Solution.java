package com.sean.leetcode.LeetCode854;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-21 08:24
 * @Description: https://leetcode.cn/problems/k-similar-strings/
 * 854. 相似度为 K 的字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，
 * 能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 */
public class Solution {

    int res = Integer.MAX_VALUE;

    public int kSimilarity(String s1, String s2) {
        dfs(s1.toCharArray(), s2.toCharArray(), 0, s2.length(), 0);
        return res;
    }

    private void dfs(char[] s1, char[] s2, int cur, int n, int ans) {
        if (cur == n) {
            res = Math.min(res, ans);
            return;
        }
        if (ans >= res) {
            return;
        }
        if (s2[cur] != s1[cur]) {
            for (int i = cur + 1; i < n; i++) {
                if (s2[i] != s1[i] && s2[cur] == s1[i]) {
                    swap(s1, i, cur);
                    dfs(s1, s2, cur + 1, n, ans + 1);
                    swap(s1, cur, i);
                }
            }
        } else {
            dfs(s1, s2, cur + 1, n, ans);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }

}
