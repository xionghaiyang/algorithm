package com.sean.leetcode.LeetCode3612;

/**
 * @Author xionghaiyang
 * @Date 2025-07-14 12:21
 * @Description https://leetcode.cn/problems/process-string-with-special-operations-i
 * 3612. 用特殊操作处理字符串 I
 * 给你一个字符串 s，它由小写英文字母和特殊字符：*、# 和 % 组成。
 * 请根据以下规则从左到右处理 s 中的字符，构造一个新的字符串 result：
 * 如果字符是 小写 英文字母，则将其添加到 result 中。
 * 字符 '*' 会 删除 result 中的最后一个字符（如果存在）。
 * 字符 '#' 会 复制 当前的 result 并 追加 到其自身后面。
 * 字符 '%' 会 反转 当前的 result。
 * 在处理完 s 中的所有字符后，返回最终的字符串 result。
 * 1 <= s.length <= 20
 * s 只包含小写英文字母和特殊字符 *、# 和 %。
 */
public class Solution {

    public String processStr(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (res.length() > 0) {
                    res.setLength(res.length() - 1);
                }
            } else if (c == '#') {
                res.append(res);
            } else if (c == '%') {
                res.reverse();
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
