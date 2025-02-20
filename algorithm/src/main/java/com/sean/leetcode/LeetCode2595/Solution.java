package com.sean.leetcode.LeetCode2595;

/**
 * @Author xionghaiyang
 * @Date 2025-02-20 09:44
 * @Description https://leetcode.cn/problems/number-of-even-and-odd-bits
 * 2595. 奇偶位数
 * 给你一个 正 整数 n 。
 * 用 even 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的偶数下标的个数。
 * 用 odd 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的奇数下标的个数。
 * 请注意，在数字的二进制表示中，位下标的顺序 从右到左。
 * 返回整数数组 answer ，其中 answer = [even, odd] 。
 */
public class Solution {

    public int[] evenOddBit(int n) {
        int[] res = new int[2];
        int index = 0;
        while (n > 0) {
            res[index] += n & 1;
            n >>= 1;
            index ^= 1;
        }
        return res;
    }

}
