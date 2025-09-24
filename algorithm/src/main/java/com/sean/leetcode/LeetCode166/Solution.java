package com.sean.leetcode.LeetCode166;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-09-24 08:17
 * @Description https://leetcode.cn/problems/fraction-to-recurring-decimal
 * 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 10^4 。
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */
public class Solution {

    public String fractionToDecimal(int numerator, int denominator) {
        long a = (long) numerator, b = (long) denominator;
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        StringBuilder res = new StringBuilder();
        if (a < 0 ^ b < 0) {
            res.append("-");
        }
        a = Math.abs(a);
        b = Math.abs(b);
        long integerPart = a / b;
        res.append(integerPart).append(".");
        StringBuilder fractionPart = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        a = a % b;
        int index = 0;
        while (a != 0 && !map.containsKey(a)) {
            map.put(a, index++);
            a *= 10;
            fractionPart.append(a / b);
            a %= b;
        }
        if (a != 0) {
            int pos = map.get(a);
            fractionPart.insert(pos, "(").append(")");
        }
        return res.append(fractionPart).toString();
    }

}
