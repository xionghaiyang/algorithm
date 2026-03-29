package com.sean.leetcode.LeetCode2839;

/**
 * @Author xionghaiyang
 * @Date 2026-03-29 08:51
 * @Description https://leetcode.cn/problems/check-if-strings-can-be-made-equal-with-operations-i
 * 2839. 判断通过操作能否让字符串相等 I
 * 给你两个字符串 s1 和 s2 ，两个字符串的长度都为 4 ，且只包含 小写 英文字母。
 * 你可以对两个字符串中的 任意一个 执行以下操作 任意 次：
 * 选择两个下标 i 和 j 且满足 j - i = 2 ，然后 交换 这个字符串中两个下标对应的字符。
 * 如果你可以让字符串 s1 和 s2 相等，那么返回 true ，否则返回 false 。
 * s1.length == s2.length == 4
 * s1 和 s2 只包含小写英文字母。
 */
public class Solution {

    public boolean canBeEqual(String s1, String s2) {
        return check(s1.charAt(0), s1.charAt(2), s2.charAt(0), s2.charAt(2)) && check(s1.charAt(1), s1.charAt(3), s2.charAt(1), s2.charAt(3));
    }

    private boolean check(char c1, char c2, char c3, char c4) {
        return (c1 == c3 && c2 == c4) || (c1 == c4 && c2 == c3);
    }

}
