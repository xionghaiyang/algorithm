package com.sean.leetcode.LeetCode2522;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 17:48
 * @Description: https://leetcode.cn/problems/partition-string-into-substrings-with-values-at-most-k/description/
 * 2522. 将字符串分割成值不超过 K 的子字符串
 * 给你一个字符串 s ，它每一位都是 1 到 9 之间的数字组成，同时给你一个整数 k 。
 * 如果一个字符串 s 的分割满足以下条件，我们称它是一个 好 分割：
 * s 中每个数位 恰好 属于一个子字符串。
 * 每个子字符串的值都小于等于 k 。
 * 请你返回 s 所有的 好 分割中，子字符串的 最少 数目。
 * 如果不存在 s 的 好 分割，返回 -1 。
 * 注意：
 * 一个字符串的 值 是这个字符串对应的整数。
 * 比方说，"123" 的值为 123 ，"1" 的值是 1 。
 * 子字符串 是字符串中一段连续的字符序列。
 */
public class Solution {

    public int minimumPartition(String s, int k) {
        int res = 1;
        int n = s.length();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) - '0' > k) {
                return -1;
            }
            sum = sum * 10 + s.charAt(i) - '0';
            if (sum > k) {
                res++;
                sum = s.charAt(i) - '0';
            }
        }
        return res;
    }

}
