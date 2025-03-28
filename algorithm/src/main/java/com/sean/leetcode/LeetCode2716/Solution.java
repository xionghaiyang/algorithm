package com.sean.leetcode.LeetCode2716;

/**
 * @Author xionghaiyang
 * @Date 2025-03-28 08:52
 * @Description https://leetcode.cn/problems/minimize-string-length
 * 2716. 最小化字符串长度
 * 给你一个下标从 0 开始的字符串 s ，重复执行下述操作 任意 次：
 * 在字符串中选出一个下标 i ，并使 c 为字符串下标 i 处的字符。
 * 并在 i 左侧（如果有）和 右侧（如果有）各 删除 一个距离 i 最近 的字符 c 。
 * 请你通过执行上述操作任意次，使 s 的长度 最小化 。
 * 返回一个表示 最小化 字符串的长度的整数。
 * 1 <= s.length <= 100
 * s 仅由小写英文字母组成
 */
public class Solution {

    public int minimizedStringLength(String s) {
        int mask = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            mask |= 1 << (s.charAt(i) - 'a');
        }
        return Integer.bitCount(mask);
    }

}
