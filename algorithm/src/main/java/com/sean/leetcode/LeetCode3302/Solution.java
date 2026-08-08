package com.sean.leetcode.LeetCode3302;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-08 09:36
 * @Description: https://leetcode.cn/problems/find-the-lexicographically-smallest-valid-sequence
 * 3302. 字典序最小的合法序列
 * 给你两个字符串 word1 和 word2 。
 * 如果一个字符串 x 修改 至多 一个字符会变成 y ，那么我们称它与 y 几乎相等 。
 * 如果一个下标序列 seq 满足以下条件，我们称它是 合法的 ：
 * 下标序列是 升序 的。
 * 将 word1 中这些下标对应的字符 按顺序 连接，得到一个与 word2 几乎相等 的字符串。
 * 请你返回一个长度为 word2.length 的数组，表示一个 字典序最小 的 合法 下标序列。
 * 如果不存在这样的序列，请你返回一个 空 数组。
 * 注意 ，答案数组必须是字典序最小的下标数组，而 不是 由这些下标连接形成的字符串。
 * 1 <= word2.length < word1.length <= 3 * 10^5
 * word1 和 word2 只包含小写英文字母。
 */
public class Solution {

    public int[] validSequence(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        int n = s.length, m = t.length;
        int[] suf = new int[n + 1];
        suf[n] = m;
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && s[i] == t[j]) {
                j--;
            }
            suf[i] = j + 1;
        }
        int[] res = new int[m];
        boolean changed = false;
        j = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == t[j] || !changed && suf[i + 1] <= j + 1) {
                if (s[i] != t[j]) {
                    changed = true;
                }
                res[j++] = i;
                if (j == m) {
                    return res;
                }
            }
        }
        return new int[]{};
    }

}
