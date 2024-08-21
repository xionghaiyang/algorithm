package com.sean.leetcode.LeetCode3007;

/**
 * @Author xionghaiyang
 * @Date 2024-08-21 08:37
 * @Description https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/
 * 3007. 价值和小于等于 K 的最大数字
 * 给你一个整数 k 和一个整数 x 。
 * 整数 num 的价值是它的二进制表示中在 x，2x，3x 等位置处
 * 设置位的数目（从最低有效位开始）。
 * 下面的表格包含了如何计算价值的例子。
 * x	num	Binary Representation	Price
 * 1	13	000001101	3
 * 2	13	000001101	1
 * 2	233	011101001	3
 * 3	13	000001101	1
 * 3	362	101101010	2
 * num 的 累加价值 是从 1 到 num 的数字的 总 价值。
 * 如果 num 的累加价值小于或等于 k 则被认为是 廉价 的。
 * 请你返回 最大 的廉价数字。
 */
public class Solution {

    public long findMaximumNumber(long k, int x) {
        long left = 1, right = (k + 1) << x;
        while (left < right) {
            long mid = (left + right + 1) / 2;
            if (accumulatedPrice(x, mid) > k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private long accumulatedPrice(int x, long num) {
        long res = 0;
        int length = 64 - Long.numberOfLeadingZeros(num);
        for (int i = x; i <= length; i += x) {
            res += accumulatedBitPrice(i, num);
        }
        return res;
    }

    private long accumulatedBitPrice(int x, long num) {
        long period = 1L << x;
        long res = period / 2 * (num / period);
        if (num % period >= period / 2) {
            res += num % period - (period / 2 - 1);
        }
        return res;
    }

}
