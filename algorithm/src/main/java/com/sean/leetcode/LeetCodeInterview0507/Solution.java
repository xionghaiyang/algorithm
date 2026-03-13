package com.sean.leetcode.LeetCodeInterview0507;

/**
 * @Author xionghaiyang
 * @Date 2026-03-13 19:45
 * @Description https://leetcode.cn/problems/exchange-lcci
 * 面试题 05.07. 配对交换
 * 配对交换。
 * 编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位 0 与位 1 交换，位 2 与位 3 交换，以此类推）。
 * num 的范围在[0, 230 - 1]之间，不会发生整数溢出。
 */
public class Solution {

    public int exchangeBits(int num) {
        int odd = num & 0b01010101010101010101010101010101;
        int even = num & 0b10101010101010101010101010101010;
        odd <<= 1;
        even >>>= 1;
        return odd | even;
    }

}
