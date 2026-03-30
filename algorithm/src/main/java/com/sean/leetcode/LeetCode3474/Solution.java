package com.sean.leetcode.LeetCode3474;

/**
 * @Author xionghaiyang
 * @Date 2026-03-31 06:34
 * @Description https://leetcode.cn/problems/lexicographically-smallest-generated-string
 * 3474. 字典序最小的生成字符串
 * 给你两个字符串，str1 和 str2，其长度分别为 n 和 m 。
 * 如果一个长度为 n + m - 1 的字符串 word 的每个下标 0 <= i <= n - 1 都满足以下条件，则称其由 str1 和 str2 生成：
 * 如果 str1[i] == 'T'，则长度为 m 的 子字符串（从下标 i 开始）与 str2 相等，即 word[i..(i + m - 1)] == str2。
 * 如果 str1[i] == 'F'，则长度为 m 的 子字符串（从下标 i 开始）与 str2 不相等，即 word[i..(i + m - 1)] != str2。
 * 返回可以由 str1 和 str2 生成 的 字典序最小 的字符串。
 * 如果不存在满足条件的字符串，返回空字符串 ""。
 * 如果字符串 a 在第一个不同字符的位置上比字符串 b 的对应字符在字母表中更靠前，则称字符串 a 的 字典序 小于 字符串 b。
 * 如果前 min(a.length, b.length) 个字符都相同，则较短的字符串字典序更小。
 * 子字符串 是字符串中的一个连续、非空 的字符序列。
 * 1 <= n == str1.length <= 10^4
 * 1 <= m == str2.length <= 500
 * str1 仅由 'T' 或 'F' 组成。
 * str2 仅由小写英文字母组成。
 */
public class Solution {

    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        char[] s1 = str1.toCharArray(), s2 = str2.toCharArray();
        char[] res = new char[n + m - 1];
        for (int i = 0; i < n; i++) {
            if (s1[i] == 'T') {
                for (int j = i; j < m + i; j++) {
                    if (res[j] == 0) {
                        res[j] = s2[j - i];
                    } else if (res[j] != s2[j - i]) {
                        return "";
                    }
                }
            }
        }
        char[] clone = res.clone();
        for (int i = 0; i < n + m - 1; i++) {
            if (res[i] == 0) {
                res[i] = 'a';
            }
        }
        for (int i = 0; i < n; i++) {
            if (s1[i] == 'F') {
                boolean flag = false;
                int index = -1;
                for (int j = i + m - 1; j >= i; j--) {
                    if (res[j] != s2[j - i]) {
                        flag = true;
                    }
                    if (index == -1 && clone[j] == 0) {
                        index = j;
                    }
                }
                if (flag) {
                    continue;
                } else if (index != -1) {
                    res[index] = 'b';
                } else {
                    return "";
                }
            }
        }
        return String.valueOf(res);
    }

}
