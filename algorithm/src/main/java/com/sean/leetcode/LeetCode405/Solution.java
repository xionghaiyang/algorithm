package com.sean.leetcode.LeetCode405;

/**
 * @Author xionghaiyang
 * @Date 2026-04-11 10:10
 * @Description https://leetcode.cn/problems/convert-a-number-to-hexadecimal
 * 405. 数字转换为十六进制数
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。
 * 对于负整数，我们通常使用 补码运算 方法。
 * 答案字符串中的所有字母都应该是小写字符，并且除了 0 本身之外，答案中不应该有任何前置零。
 * 注意: 不允许使用任何由库提供的将数字直接转换或格式化为十六进制的方法来解决这个问题。
 * -2^31 <= num <= 2^31 - 1
 */
public class Solution {

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        char[] str = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            int i = num & 0xf;
            res.append(str[i]);
            num >>>= 4;
        }
        return res.reverse().toString();
    }

}
