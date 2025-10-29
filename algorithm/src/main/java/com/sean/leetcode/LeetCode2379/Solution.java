package com.sean.leetcode.LeetCode2379;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-09 08:03
 * @Description: https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks
 * 2379. 得到 K 个黑块的最少涂色次数
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。
 * 字符 'W' 和 'B' 分别表示白色和黑色。
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i] 要么是 'W' ，要么是 'B' 。
 * 1 <= k <= n
 */
public class Solution {

    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        char[] str = blocks.toCharArray();
        int cntW = 0;
        for (int i = 0; i < k; i++) {
            cntW += str[i] == 'W' ? 1 : 0;
        }
        int res = cntW;
        for (int i = k; i < n; i++) {
            cntW += (str[i] == 'W' ? 1 : 0) - (str[i - k] == 'W' ? 1 : 0);
            res = Math.min(res, cntW);
        }
        return res;
    }

}
