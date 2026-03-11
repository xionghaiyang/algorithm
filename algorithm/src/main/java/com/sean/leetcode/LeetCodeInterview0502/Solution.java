package com.sean.leetcode.LeetCodeInterview0502;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-02 08:56
 * @Description: https://leetcode.cn/problems/bianry-number-to-string-lcci/
 * 面试题 05.02. 二进制数转字符串
 * 二进制数转字符串。
 * 给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
 * 如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 */
public class Solution {

    public String printBin(double num) {
        StringBuilder res = new StringBuilder("0.");
        while (res.length() <= 32 && num != 0) {
            num *= 2;
            int digit = (int) num;
            res.append(digit);
            num -= digit;
        }
        return res.length() <= 32 ? res.toString() : "ERROR";
    }

}
