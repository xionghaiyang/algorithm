package com.sean.leetcode.LeetCodeInterview0813;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-18 20:46
 * @Description https://leetcode.cn/problems/pile-box-lcci
 * 面试题 08.13. 堆箱子
 * 堆箱子。
 * 给你一堆n个箱子，箱子宽 wi、深 di、高 hi。
 * 箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。
 * 实现一种方法，搭出最高的一堆箱子。
 * 箱堆的高度为每个箱子高度的总和。
 * 输入使用数组[wi, di, hi]表示每个箱子。
 * 箱子的数目不大于3000个。
 */
public class Solution {

    public int pileBox(int[][] box) {
        Arrays.sort(box, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        int n = box.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = box[i][2];
            for (int j = 0; j < i; j++) {
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + box[i][2]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
