package com.sean.leetcode.LeetCode1691;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-12 08:36
 * @Description: https://leetcode.cn/problems/maximum-height-by-stacking-cuboids/
 * 1691. 堆叠长方体的最大高度
 * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。
 * 请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
 * 如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。
 * 你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
 * 返回 堆叠长方体 cuboids 可以得到的 最大高度 。
 */
public class Solution {

    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (a, b) -> (a[0] + a[1] + a[2]) - (b[0] + b[1] + b[2]));
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(cuboids, memo, -1, 0);
    }

    private int dfs(int[][] cuboids, int[] memo, int top, int index) {
        if (index == cuboids.length) {
            return 0;
        }
        if (top != -1 && memo[top] != -1) {
            return memo[top];
        }
        int height = dfs(cuboids, memo, top, index + 1);
        if (top == -1 || check(cuboids[top], cuboids[index])) {
            height = Math.max(height, cuboids[index][2] + dfs(cuboids, memo, index, index + 1));
        }
        if (top != -1) {
            memo[top] = height;
        }
        return height;
    }

    private boolean check(int[] a, int[] b) {
        return a[0] <= b[0] && a[1] <= b[1] && a[2] <= b[2];
    }

}
