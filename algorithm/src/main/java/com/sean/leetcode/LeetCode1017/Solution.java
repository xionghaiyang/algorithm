package com.sean.leetcode.LeetCode1017;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-06 08:07
 * @Description: https://leetcode.cn/problems/convert-to-base-2/
 * 1017. 负二进制转换
 * 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
 * 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 */
public class Solution {

    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            int x = n & 1;
            res.append(x);
            n -= x;
            n /= -2;
        }
        return res.reverse().toString();
    }

}
