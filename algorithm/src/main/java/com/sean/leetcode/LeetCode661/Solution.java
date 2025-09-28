package com.sean.leetcode.LeetCode661;

/**
 * @Author xionghaiyang
 * @Date 2025-09-28 19:01
 * @Description https://leetcode.cn/problems/image-smoother
 * 661. 图片平滑器
 * 图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
 * 每个单元格的  平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
 * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
 * 给你一个表示图像灰度的 m x n 整数矩阵 img ，返回对图像的每个单元格平滑处理后的图像 。
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 */
public class Solution {

    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int total = 0, cnt = 0;
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        total += img[x][y];
                        cnt++;
                    }
                }
                res[i][j] = total / cnt;
            }
        }
        return res;
    }

    public int[][] imageSmoother1(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + img[i][j];
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int upper = Math.max(0, i - 1);
                int bottom = Math.min(m - 1, i + 1);
                int left = Math.max(0, j - 1);
                int right = Math.min(n - 1, j + 1);
                int area = (bottom - upper + 1) * (right - left + 1);
                int sum = preSum[bottom + 1][right + 1] - preSum[upper][right + 1] - preSum[bottom + 1][left] + preSum[upper][left];
                res[i][j] = sum / area;
            }
        }
        return res;
    }

}
