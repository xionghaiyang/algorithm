package com.sean.leetcode.LeetCode793;

/**
 * @Author xionghaiyang
 * @Date 2022-08-28 09:18
 * @Description https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/
 * 793. 阶乘函数后 K 个零
 *  f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 */
public class Solution {

    public int preimageSizeFZF(int k) {
        return (int) (help(k + 1) - help(k));
    }

    private long help(int k) {
        long left = 0L;
        long right = 5L * k;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (zeta(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;
    }

    private long zeta(long x) {
        long res = 0L;
        while (x != 0) {
            res += x / 5;
            x /= 5;
        }
        return res;
    }

}
