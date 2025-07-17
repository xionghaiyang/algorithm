package com.sean.leetcode.LeetCode3614;

/**
 * @Author xionghaiyang
 * @Date 2025-07-17 09:55
 * @Description https://leetcode.cn/problems/process-string-with-special-operations-ii
 * 3614. 用特殊操作处理字符串 II
 * 给你一个字符串 s，由小写英文字母和特殊字符：'*'、'#' 和 '%' 组成。
 * 同时给你一个整数 k。
 * 请根据以下规则从左到右处理 s 中每个字符，构造一个新的字符串 result：
 * 如果字符是 小写 英文字母，则将其添加到 result 中。
 * 字符 '*' 会 删除 result 中的最后一个字符（如果存在）。
 * 字符 '#' 会 复制 当前的 result 并追加到其自身后面。
 * 字符 '%' 会 反转 当前的 result。
 * 返回最终字符串 result 中第 k 个字符（下标从 0 开始）。
 * 如果 k 超出 result 的下标索引范围，则返回 '.'。
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母和特殊字符 '*'、'#' 和 '%'。
 * 0 <= k <= 10^15
 * 处理 s 后得到的 result 的长度不超过 10^15。
 */
public class Solution {

    public char processStr(String s, long k) {
        char[] str = s.toCharArray();
        int n = s.length();
        long size = 0;
        for (char c : str) {
            if (c == '*') {
                size = Math.max(size - 1, 0);
            } else if (c == '#') {
                size <<= 1;
            } else if (c != '%') {
                size++;
            }
        }
        if (k >= size) {
            return '.';
        }
        for (int i = n - 1; ; i--) {
            char c = str[i];
            if (c == '*') {
                size++;
            } else if (c == '#') {
                size >>= 1;
                if (k >= size) {
                    k -= size;
                }
            } else if (c == '%') {
                k = size - 1 - k;
            } else {
                size--;
                if (k == size) {
                    return c;
                }
            }
        }
    }

}
