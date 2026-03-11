package com.sean.leetcode.LeetCodeInterview0504;

/**
 * @Author xionghaiyang
 * @Date 2026-03-11 19:01
 * @Description https://leetcode.cn/problems/closed-number-lcci
 * 面试题 05.04. 下一个数
 * 下一个数。
 * 给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 * num 的范围在[1, 2147483647]之间；
 * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 */
public class Solution {

    public int[] findClosedNumbers(int num) {
        return new int[]{getMax(num), getMin(num)};
    }

    private int getMax(int num) {
        if (num == Integer.MAX_VALUE) {
            return -1;
        }
        //num加上num二进制中最右侧的1(lowbit)
        //例如 num为11100 lowbit为100 11100 + 100 = 100000
        int x = num + (num & (-num));
        int diff = Integer.bitCount(num) - Integer.bitCount(x);
        //从右往左补上1
        for (int i = 0; i < diff; i++) {
            x |= (1 << i);
        }
        return x;
    }

    private int getMin(int num) {
        //全是1
        if (num == Integer.MAX_VALUE || (num & (num + 1)) == 0) {
            return -1;
        }
        //找到num二进制中最右侧的0
        //num是10000111减去 1000  10000111 - 1000 = 1111111
        int x = ~num;
        x = num - (x & (-x));
        int diff = Integer.bitCount(x) - Integer.bitCount(num);
        //左移后再右移
        return (x >> diff) << diff;
    }

}
