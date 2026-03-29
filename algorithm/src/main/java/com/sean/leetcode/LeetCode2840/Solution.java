package com.sean.leetcode.LeetCode2840;

/**
 * @Author xionghaiyang
 * @Date 2026-03-30 06:32
 * @Description https://leetcode.cn/problems/check-if-strings-can-be-made-equal-with-operations-ii
 * 2840. 判断通过操作能否让字符串相等 II
 * 给你两个字符串 s1 和 s2 ，两个字符串长度都为 n ，且只包含 小写 英文字母。
 * 你可以对两个字符串中的 任意一个 执行以下操作 任意 次：
 * 选择两个下标 i 和 j ，满足 i < j 且 j - i 是 偶数，然后 交换 这个字符串中两个下标对应的字符。
 * 如果你可以让字符串 s1 和 s2 相等，那么返回 true ，否则返回 false 。
 * n == s1.length == s2.length
 * 1 <= n <= 10^5
 * s1 和 s2 只包含小写英文字母。
 */
public class Solution {

    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[][] cnt = new int[26][2];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a'][i & 1]++;
            cnt[s2.charAt(i) - 'a'][i & 1]--;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i][0] != 0 || cnt[i][1] != 0) {
                return false;
            }
        }
        return true;
    }

}
