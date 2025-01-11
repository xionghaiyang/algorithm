package com.sean.leetcode.LeetCode3270;

/**
 * @Author xionghaiyang
 * @Date 2025-01-11 23:02
 * @Description https://leetcode.cn/problems/find-the-key-of-the-numbers/
 * 3270. 求出数字答案
 * 给你三个 正 整数 num1 ，num2 和 num3 。
 * 数字 num1 ，num2 和 num3 的数字答案 key 是一个四位数，定义如下：
 * 一开始，如果有数字 少于 四位数，给它补 前导 0 。
 * 答案 key 的第 i 个数位（1 <= i <= 4）为 num1 ，num2 和 num3 第 i 个数位中的 最小 值。
 * 请你返回三个数字 没有 前导 0 的数字答案。
 */
public class Solution {

    public int generateKey(int num1, int num2, int num3) {
        int key = 0;
        for (int p = 1; num1 > 0 && num2 > 0 && num3 > 0; p *= 10) {
            key += Math.min(Math.min(num1 % 10, num2 % 10), num3 % 10) * p;
            num1 /= 10;
            num2 /= 10;
            num3 /= 10;
        }
        return key;
    }

}
