package com.sean.leetcode.LeetCode8;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-03 18:55
 * @Description: https://leetcode.cn/problems/string-to-integer-atoi
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 * 函数 myAtoi(string s) 的算法如下：
 * 空格：读入字符串并丢弃无用的前导空格（" "）
 * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。
 * 如果两者都不存在，则假定结果为正。
 * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。
 * 如果没有读取数字，则结果为0。
 * 舍入：如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −2^31 的整数应该被舍入为 −2^31 ，大于 2^31 − 1 的整数应该被舍入为 2^31 − 1 。
 * 返回整数作为最终结果。
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class Solution {

    public int myAtoi(String s) {
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 0, sign = 1, n = s.length();
        if (n == 0) {
            return 0;
        }
        while (s.charAt(i) == ' ') {
            if (++i == n) {
                return 0;
            }
        }
        if (s.charAt(i) == '-') {
            sign = -1;
        }
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            i++;
        }
        for (int j = i; j < n; j++) {
            if (s.charAt(j) < '0' || s.charAt(j) > '9') {
                break;
            }
            if (res > bndry || res == bndry && s.charAt(j) > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (s.charAt(j) - '0');
        }
        return sign * res;
    }

}
