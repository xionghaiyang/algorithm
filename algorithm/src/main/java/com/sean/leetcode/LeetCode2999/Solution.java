package com.sean.leetcode.LeetCode2999;

/**
 * @Author xionghaiyang
 * @Date 2025-04-10 08:58
 * @Description https://leetcode.cn/problems/count-the-number-of-powerful-integers
 * 2999. 统计强大整数的数目
 * 给你三个整数 start ，finish 和 limit 。
 * 同时给你一个下标从 0 开始的字符串 s ，表示一个 正 整数。
 * 如果一个 正 整数 x 末尾部分是 s （换句话说，s 是 x 的 后缀），且 x 中的每个数位至多是 limit ，那么我们称 x 是 强大的 。
 * 请你返回区间 [start..finish] 内强大整数的 总数目 。
 * 如果一个字符串 x 是 y 中某个下标开始（包括 0 ），到下标为 y.length - 1 结束的子字符串，那么我们称 x 是 y 的一个后缀。
 * 比方说，25 是 5125 的一个后缀，但不是 512 的后缀。
 * 1 <= start <= finish <= 10^15
 * 1 <= limit <= 9
 * 1 <= s.length <= floor(log10(finish)) + 1
 * s 数位中每个数字都小于等于 limit 。
 * s 不包含任何前导 0 。
 */
public class Solution {

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return process(Long.toString(finish), s, limit) - process(Long.toString(start - 1), s, limit);
    }

    private long process(String x, String s, int limit) {
        if (x.length() < s.length()) {
            return 0;
        }
        if (x.length() == s.length()) {
            return x.compareTo(s) >= 0 ? 1 : 0;
        }
        String suffix = x.substring(x.length() - s.length());
        long res = 0;
        int preLen = x.length() - s.length();
        for (int i = 0; i < preLen; i++) {
            int digit = x.charAt(i) - '0';
            if (limit < digit) {
                res += (long) Math.pow(limit + 1, preLen - i);
                return res;
            }
            res += (long) (digit) * (long) Math.pow(limit + 1, preLen - 1 - i);
        }
        if (suffix.compareTo(s) >= 0) {
            res++;
        }
        return res;
    }

}
