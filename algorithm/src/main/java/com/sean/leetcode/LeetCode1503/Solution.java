package com.sean.leetcode.LeetCode1503;

/**
 * @Author xionghaiyang
 * @Date 2025-08-21 06:59
 * @Description https://leetcode.cn/problems/count-submatrices-with-all-ones
 * 1504. 统计全 1 子矩形
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 * 1 <= m, n <= 150
 * mat[i][j] 仅包含 0 或 1
 */
public class Solution {

    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int res = 0;
        for (int top = 0; top < m; top++) {
            int[] arr = new int[n];
            for (int bottom = top; bottom < m; bottom++) {
                int h = bottom - top + 1;
                int last = -1;
                for (int j = 0; j < n; j++) {
                    arr[j] += mat[bottom][j];
                    if (arr[j] != h) {
                        last = j;
                    } else {
                        res += j - last;
                    }
                }
            }
        }
        return res;
    }

}
