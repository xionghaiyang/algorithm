package com.sean.leetcode.LeetCode201;

/**
 * @Author xionghaiyang
 * @Date 2025-07-24 12:11
 * @Description https://leetcode.cn/problems/bitwise-and-of-numbers-range
 * 201. 数字范围按位与
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * 0 <= left <= right <= 2^31 - 1
 */
public class Solution {

    public int rangeBitwiseAnd(int left, int right) {
        int cnt = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            cnt++;
        }
        return left << cnt;
    }

    public int rangeBitwiseAnd1(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }

}
