package com.sean.leetcode.LeetCode1415;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 07:47
 * @Description https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n
 * 1415. 长度为 n 的开心字符串中字典序第 k 小的字符串
 * 一个 「开心字符串」定义为：
 * 仅包含小写字母 ['a', 'b', 'c'].
 * 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
 * 比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字符串。
 * 给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。
 * 请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
 * 1 <= n <= 10
 * 1 <= k <= 100
 */
public class Solution {

    public String getHappyString(int n, int k) {
        char[] chs = {'a', 'b', 'c'};
        if (k > 3 << (n - 1)) {
            return "";
        }
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            int cnt = 1 << (n - i - 1);
            for (char c : chs) {
                if (i > 0 && res[i - 1] == c) {
                    continue;
                }
                if (k <= cnt) {
                    res[i] = c;
                    break;
                }
                k -= cnt;
            }
        }
        return String.valueOf(res);
    }

}
