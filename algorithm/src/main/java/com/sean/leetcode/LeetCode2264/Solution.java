package com.sean.leetcode.LeetCode2264;

/**
 * @Author xionghaiyang
 * @Date 2025-01-29 08:07
 * @Description https://leetcode.cn/problems/largest-3-same-digit-number-in-string
 * 2264. 字符串中最大的 3 位相同数字
 * 给你一个字符串 num ，表示一个大整数。
 * 如果一个整数满足下述所有条件，则认为该整数是一个 优质整数 ：
 * 该整数是 num 的一个长度为 3 的 子字符串 。
 * 该整数由唯一一个数字重复 3 次组成。
 * 以字符串形式返回 最大的优质整数 。
 * 如果不存在满足要求的整数，则返回一个空字符串 "" 。
 * 注意：
 * 子字符串 是字符串中的一个连续字符序列。
 * num 或优质整数中可能存在 前导零 。
 * 3 <= num.length <= 1000
 * num 仅由数字（0 - 9）组成
 */
public class Solution {

    public String largestGoodInteger(String num) {
        int n = num.length();
        String res = "";
        for (int i = 0; i < n - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
                String cur = num.substring(i, i + 3);
                if (res.compareTo(cur) < 0) {
                    res = cur;
                }
            }
        }
        return res;
    }

}
