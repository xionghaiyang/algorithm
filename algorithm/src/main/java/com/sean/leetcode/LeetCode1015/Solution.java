package com.sean.leetcode.LeetCode1015;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-10 08:11
 * @Description: https://leetcode.cn/problems/smallest-integer-divisible-by-k/
 * 1015. 可被 K 整除的最小整数
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
 * 返回 n 的长度。如果不存在这样的 n ，就返回-1。
 * 注意： n 不符合 64 位带符号整数。
 */
public class Solution {

    public int smallestRepunitDivByK(int k) {
        int rest = 1 % k;
        int res = 1;
        Set<Integer> set = new HashSet<>();
        set.add(rest);
        while (rest != 0) {
            rest = (rest * 10 + 1) % k;
            res++;
            if (set.contains(rest)) {
                return -1;
            }
            set.add(rest);
        }
        return res;
    }

}
