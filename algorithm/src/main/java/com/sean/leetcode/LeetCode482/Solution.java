package com.sean.leetcode.LeetCode482;

/**
 * @Author xionghaiyang
 * @Date 2026-04-09 19:56
 * @Description https://leetcode.cn/problems/license-key-formatting
 * 482. 密钥格式化
 * 给定一个许可密钥字符串 s，仅由字母、数字字符和破折号组成。
 * 字符串由 n 个破折号分成 n + 1 组。
 * 你也会得到一个整数 k 。
 * 我们想要重新格式化字符串 s，使每一组包含 k 个字符，除了第一组，它可以比 k 短，但仍然必须包含至少一个字符。
 * 此外，两组之间必须插入破折号，并且应该将所有小写字母转换为大写字母。
 * 返回 重新格式化的许可密钥 。
 * 1 <= s.length <= 10^5
 * s 只包含字母、数字和破折号 '-'.
 * 1 <= k <= 10^4
 */
public class Solution {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder res = new StringBuilder();
        int i = s.length() - 1, j = 0;
        while (i >= 0) {
            char c = s.charAt(i);
            if (c != '-') {
                res.append(Character.toUpperCase(c));
                if (++j == k) {
                    j = 0;
                    res.append('-');
                }
            }
            i--;
        }
        if (res.length() > 0 && res.charAt(res.length() - 1) == '-') {
            res.deleteCharAt(res.length() - 1);
        }
        return res.reverse().toString();
    }

}
