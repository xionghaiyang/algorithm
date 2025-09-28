package com.sean.leetcode.LeetCode3697;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-09-28 12:32
 * @Description https://leetcode.cn/problems/compute-decimal-representation
 * 3697. 计算十进制表示
 * 给你一个 正整数 n。
 * 如果一个正整数可以表示为 1 到 9 的单个数字和 10 的非负整数次幂的乘积，则称这个整数是一个 10 进制分量。
 * 例如，500、30 和 7 是 10 进制分量 ，而 537、102 和 11 则不是。
 * 请将 n 表示为若干 仅由 10 进制分量组成的和，且使用的 10 进制分量个数 最少 。
 * 返回一个包含这些 10 进制分量 的数组，并按分量大小 降序 排列。
 */
public class Solution {

    public int[] decimalRepresentation(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        int base = 1;
        while (n > 0) {
            int x = n % 10;
            if (x != 0) {
                stack.push(x * base);
            }
            n /= 10;
            base *= 10;
        }
        int m = stack.size();
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

}
