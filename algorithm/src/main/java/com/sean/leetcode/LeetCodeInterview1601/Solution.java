package com.sean.leetcode.LeetCodeInterview1601;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 20:53
 * @Description https://leetcode.cn/problems/swap-numbers-lcci
 * 面试题 16.01. 交换数字
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 * numbers.length == 2
 * -2147483647 <= numbers[i] <= 2147483647
 */
public class Solution {

    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }

}
