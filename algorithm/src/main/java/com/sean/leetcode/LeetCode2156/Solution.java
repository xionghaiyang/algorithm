package com.sean.leetcode.LeetCode2156;

/**
 * @Author xionghaiyang
 * @Date 2025-11-20 19:03
 * @Description https://leetcode.cn/problems/find-substring-with-given-hash-value
 * 2156. 查找给定哈希值的子串
 * 给定整数 p 和 m ，一个长度为 k 且下标从 0 开始的字符串 s 的哈希值按照如下函数计算：
 * hash(s, p, m) = (val(s[0]) * p^0 + val(s[1]) * p^1 + ... + val(s[k-1]) * p^k-1) mod m.
 * 其中 val(s[i]) 表示 s[i] 在字母表中的下标，从 val('a') = 1 到 val('z') = 26 。
 * 给你一个字符串 s 和整数 power，modulo，k 和 hashValue 。
 * 请你返回 s 中 第一个 长度为 k 的 子串 sub ，满足 hash(sub, power, modulo) == hashValue 。
 * 测试数据保证一定 存在 至少一个这样的子串。
 * 子串 定义为一个字符串中连续非空字符组成的序列。
 * 1 <= k <= s.length <= 2 * 10^4
 * 1 <= power, modulo <= 10^9
 * 0 <= hashValue < modulo
 * s 只包含小写英文字母。
 * 测试数据保证一定 存在 满足条件的子串。
 */
public class Solution {

    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        char[] str = s.toCharArray();
        int n = s.length();
        long hash = 0, pk = 1;
        for (int i = n - 1; i >= n - k; i--) {
            hash = (hash * power + (str[i] & 31)) % modulo;
            pk = pk * power % modulo;
        }
        int res = hash == hashValue ? n - k : 0;
        for (int i = n - k - 1; i >= 0; i--) {
            hash = (hash * power + (str[i] & 31) - pk * (str[i + k] & 31) % modulo + modulo) % modulo;
            if (hash == hashValue) {
                res = i;
            }
        }
        return s.substring(res, res + k);
    }

}
