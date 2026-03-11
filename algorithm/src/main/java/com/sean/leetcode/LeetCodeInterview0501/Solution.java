package com.sean.leetcode.LeetCodeInterview0501;

/**
 * @Author xionghaiyang
 * @Date 2026-03-11 12:17
 * @Description https://leetcode.cn/problems/insert-into-bits-lcci
 * 面试题 05.01. 插入
 * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。
 * 具体插入过程如图所示。
 * 题目保证从 i 位到 j 位足以容纳 M， 例如： M = 10011，则 i～j 区域至少可容纳 5 位。
 */
public class Solution {

    public int insertBits(int N, int M, int i, int j) {
        return (N & (~(((1 << (j - i + 1)) - 1) << i))) | (M << i);
    }

}
