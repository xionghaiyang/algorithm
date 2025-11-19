package com.sean.leetcode.LeetCode1888;

/**
 * @Author xionghaiyang
 * @Date 2025-11-19 12:17
 * @Description https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating
 * 1888. 使二进制字符串字符交替的最少反转次数
 * 给你一个二进制字符串 s 。
 * 你可以按任意顺序执行以下两种操作任意次：
 * 类型 1 ：删除 字符串 s 的第一个字符并将它 添加 到字符串结尾。
 * 类型 2 ：选择 字符串 s 中任意一个字符并将该字符 反转 ，也就是如果值为 '0' ，则反转得到 '1' ，反之亦然。
 * 请你返回使 s 变成 交替 字符串的前提下， 类型 2 的 最少 操作次数 。
 * 我们称一个字符串是 交替 的，需要满足任意相邻字符都不同。
 * 比方说，字符串 "010" 和 "1010" 都是交替的，但是字符串 "0100" 不是。
 * 1 <= s.length <= 10^5
 * s[i] 要么是 '0' ，要么是 '1' 。
 */
public class Solution {

    public int minFlips(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        int res = n;
        for (int i = 0, cnt = 0; i < n * 2 - 1; i++) {
            if (str[i % n] % 2 != i % 2) {
                cnt++;
            }
            int left = i - n + 1;
            if (left < 0) {
                continue;
            }
            res = Math.min(res, Math.min(cnt, n - cnt));
            if (str[left] % 2 != left % 2) {
                cnt--;
            }
        }
        return res;
    }

}
