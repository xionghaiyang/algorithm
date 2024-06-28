package com.sean.leetcode.LeetCode2379;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-09 08:03
 * @Description: https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
 * 2379. 得到 K 个黑块的最少涂色次数
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。
 * 字符 'W' 和 'B' 分别表示白色和黑色。
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 */
public class Solution {

    public int minimumRecolors(String blocks, int k) {
        if (blocks == null || blocks.length() < k) {
            return -1;
        }
        int n = blocks.length();
        int left = 0, right = 0, count = 0;
        while (right < k) {
            count += blocks.charAt(right) == 'W' ? 1 : 0;
            right++;
        }
        int res = count;
        while (right < n) {
            count += blocks.charAt(right) == 'W' ? 1 : 0;
            count -= blocks.charAt(left) == 'W' ? 1 : 0;
            res = Math.min(res, count);
            left++;
            right++;
        }
        return res;
    }

}
